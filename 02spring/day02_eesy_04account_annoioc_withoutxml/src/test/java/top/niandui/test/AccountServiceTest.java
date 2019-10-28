package top.niandui.test;

import config.SpringConfiguration;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.niandui.domain.Account;
import top.niandui.service.IAccountService;

import javax.swing.text.html.FormView;
import java.util.List;

/**
 * 使用Junit单元测试：测试我们的配置
 */
public class AccountServiceTest {

    @Test
    public void testFindAll() {
        // 1. 获取容器
//        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);

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
        // 1. 获取容器
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        // 2. 得到业务层对象
        IAccountService accountService = ac.getBean("accountService", IAccountService.class);

        // 3. 执行方法
        Account account = accountService.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testFindSave() {
        // 1. 获取容器
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        // 2. 得到业务层对象
        IAccountService accountService = ac.getBean("accountService", IAccountService.class);

        // 3. 执行方法
        Account account = new Account();
        account.setName("test annotation");
        account.setMoney(12345F);

        accountService.saveAccount(account);
    }

    @Test
    public void testFindUpdate() {
        // 1. 获取容器
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        // 2. 得到业务层对象
        IAccountService accountService = ac.getBean("accountService", IAccountService.class);

        // 3. 执行方法
        Account account = accountService.findAccountById(4);
        account.setMoney(23456F);

        accountService.updateAccount(account);
    }

    @Test
    public void testFindDelete() {
        // 1. 获取容器
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        // 2. 得到业务层对象
        IAccountService accountService = ac.getBean("accountService", IAccountService.class);

        // 3. 执行方法
        accountService.deleteAccount(4);
    }
}
