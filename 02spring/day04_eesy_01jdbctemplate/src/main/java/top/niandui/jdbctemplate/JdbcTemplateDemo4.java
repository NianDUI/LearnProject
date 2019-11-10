package top.niandui.jdbctemplate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import top.niandui.dao.IAccountDao;
import top.niandui.domain.Account;

/**
 * JdbcTemplate的最基本用法
 */
public class JdbcTemplateDemo4 {

    public static void main(String[] args) {
        // 1、获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");

        // 2、获取对象
        IAccountDao accountDao = ac.getBean("accountDao", IAccountDao.class);

        // 3、执行操作
        Account account = accountDao.findAccountById(1);
        System.out.println(account);

        account.setMoney(30000f);
        accountDao.updateAccount(account);

        account = accountDao.findAccountByName("aaa");
        System.out.println(account);



    }

}
