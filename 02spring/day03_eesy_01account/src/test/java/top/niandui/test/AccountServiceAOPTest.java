package top.niandui.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.niandui.service.IAccountService;

/**
 * 使用Junit单元测试：测试我们的配置，基于AOP的事务配置
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean_aop.xml")
public class AccountServiceAOPTest {

    @Autowired
    @Qualifier("accountService")
    private IAccountService accountService;

    @Test
    public void testTransfer() {
        accountService.transfer("aaa", "bbb", 100f );
    }


    @Test
    public void test() {
        accountService.test();
    }
}
