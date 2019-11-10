package top.niandui.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.niandui.service.IAccountService;

/**
 * @Title: AOPTest.java
 * @description: TODO
 * @time: 2019/11/7 21:15
 * @author: liyongda
 * @version: 1.0
 *
 * 测试AOP的配置
 */
public class AOPTest {

    public static void main(String[] args) {
        // 1、获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");

        // 2、获取对象
        IAccountService as = ac.getBean("accountService", IAccountService.class);

        // 3、执行方法
        as.saveAccount();
//        as.updateAccount(1);
//        as.deleteAccount();

    }
}
