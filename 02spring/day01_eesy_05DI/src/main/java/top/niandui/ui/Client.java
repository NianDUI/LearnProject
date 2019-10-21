package top.niandui.ui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.niandui.service.IAccountService;

/**
 * 模拟一个表现层，用于调用业务层
 */
public class Client {
    /**
     * 获取spring的Ioc核心容器，本根据id获取对象
     * @param args
     */
    public static void main(String[] args) {
        // 1. 获取核心容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");

        // 2. 根据id胡Bean对象
//        IAccountService as1 = (IAccountService) ac.getBean("accountService");
//        as1.saveAccount();

//        IAccountService as2 = (IAccountService) ac.getBean("accountService2");
//        as2.saveAccount();

        IAccountService as3 = (IAccountService) ac.getBean("accountService3");
        as3.saveAccount();
    }
}
