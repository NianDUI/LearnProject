package top.niandui.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.niandui.service.IAccountService;

/**
 * spring测试类，service和dao
 */
public class TestSpring {

    @Test
    public void run1() {
        // 加载配置文件
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 获取对象
        IAccountService as = ac.getBean("accountService", IAccountService.class);

        // 调用方法
        as.findAll();

    }
}
