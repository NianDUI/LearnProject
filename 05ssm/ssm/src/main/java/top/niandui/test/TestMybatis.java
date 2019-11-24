package top.niandui.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import top.niandui.dao.IAccountDao;
import top.niandui.domain.Account;
import top.niandui.service.IAccountService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Mybatis测试类
 */
public class TestMybatis {

    /**
     * 测试查询
     * @throws Exception
     */
    @Test
    public void run1() throws Exception {
        // 记载mybatis配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");

        // 创建一个SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        // 创建一个SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 获取到代理对象
        IAccountDao dao = sqlSession.getMapper(IAccountDao.class);
        // 查询所有的账户的信息
        List<Account> list = dao.findAll();
        for (Account account : list) {
            System.out.println(account);
        }

        // 关闭资源
        sqlSession.close();
        in.close();

    }

    /**
     * 测试保存
     * @throws Exception
     */
    @Test
    public void run2() throws Exception {
        // 记载mybatis配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");

        // 创建一个SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        // 创建一个SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 获取到代理对象
        IAccountDao dao = sqlSession.getMapper(IAccountDao.class);

        // 创建账户对象
        Account account = new Account();
        account.setName("ccc");
        account.setMoney(333d);

        // 保存账户信息
        dao.saveAccount(account);

        // 提交事务
        sqlSession.commit();

        // 关闭资源
        sqlSession.close();
        in.close();
    }
}
