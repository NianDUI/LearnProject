package top.niandui.ui;

import top.niandui.factory.BeanFactory;
import top.niandui.service.IAccountService;
import top.niandui.service.Impl.AccountServiceImpl;

/**
 * 模拟一个表现层，用于调用业务层
 */
public class Client {
    public static void main(String[] args) {
//        IAccountService accountService = new AccountServiceImpl();
        for (int i = 0; i < 5; i++) {
            IAccountService accountService = (IAccountService) BeanFactory.getBean("accountService");
            System.out.println(accountService);
            accountService.saveAccount();
        }
    }
}
