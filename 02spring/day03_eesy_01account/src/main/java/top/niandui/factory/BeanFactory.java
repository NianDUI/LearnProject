package top.niandui.factory;

import top.niandui.service.IAccountService;
import top.niandui.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 用于创建Service代理对象的工厂
 */
public class BeanFactory {

    private IAccountService accountService;

    private TransactionManager txManager;

    public void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    /**
     * 获取Service代理对象
     * @return
     */
    public IAccountService getAccountService() {
        IAccountService proxyAccountService = (IAccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(),
                accountService.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * 添加事务的支持
                     * @param proxy
                     * @param method
                     * @param args
                     * @return
                     * @throws Throwable
                     */
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        try {
                            if ("test".equals(method.getName())) {
                                return method.invoke(accountService, args);
                            }
                            Object rtValue = null;
                            // 1. 开启事务
                            txManager.beginTransaction();
                            // 2. 执行操作
                            rtValue = method.invoke(accountService, args);
                            // 3. 提交事务
                            txManager.commit();
                            // 4. 返回结果
                            return rtValue;
                        } catch (Exception e) {
                            // 5. 回滚操作
                            txManager.rollback();
                            throw new RuntimeException(e);
                        } finally {
                            // 6. 释放连接
                            txManager.release();
                        }
                    }
                });
        return proxyAccountService;
    }
}
