package top.niandui.test;

import config.JdbcConfig;
import config.SpringConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.niandui.domain.Account;
import top.niandui.service.IAccountService;

import javax.swing.text.html.FormView;
import java.util.List;

/**
 * 使用Junit单元测试：测试我们的配置
 *
 * Spring整合Junit的配置
 *      1. 导入spring整合junit的jar（坐标）
 *      2. 使用Junit提供的一个注解把原有的main方法替换了，替换成spring提供的
 *          @RunWith：
 *              作用：替换junit原有的main方法
 *      3. 告知spring的运行器，spring和ioc的创建是基于xml还是注解的，并说明位置
 *          @ContextConfiguration
 *                  locations：指定xml文件的位置，加上classpath关键字，表示在类路径下
 *                  classes：指定注解类所在的位置
 *
 *  当我们使用spring 5.x版本的时候，要求junit的jar必须在4.12及以上
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class AccountServiceTest {

    // 方法一：
//    private ApplicationContext ac;
//    private IAccountService accountService;
//    @Before
//    public void init() {
//        // 1. 获取容器
//        ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
//        // 2. 得到业务层对象
//        accountService = ac.getBean("accountService", IAccountService.class);
//    }

    // 方法二，加上该类上的两个注解
    @Autowired
    private IAccountService accountService;

    @Test
    public void testFindAll() {
        // 1. 获取容器
//        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        // 配置类为并列的关系
//        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class, JdbcConfig.class);

        // 2. 得到业务层对象
        IAccountService accountService = ac.getBean("accountService", IAccountService.class);

        // 3. 执行方法
        List<Account> accounts = accountService.findAllAccount();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void testFindOne() {
        // 3. 执行方法
        Account account = accountService.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testFindSave() {
        // 3. 执行方法
        Account account = new Account();
        account.setName("test annotation");
        account.setMoney(12345F);

        accountService.saveAccount(account);
    }

    @Test
    public void testFindUpdate() {
        // 3. 执行方法
        Account account = accountService.findAccountById(4);
        account.setMoney(23456F);

        accountService.updateAccount(account);
    }

    @Test
    public void testFindDelete() {
        // 3. 执行方法
        accountService.deleteAccount(4);
    }
}
