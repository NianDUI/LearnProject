package top.niandui.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import top.niandui.springboot.bean.Person;
import top.niandui.springboot.service.HelloService;

/**
 * SpringBoot 单元测试
 * <p>
 * 可以在测试期间很方便的类似编码一样进行自动注入容器的功能
 */
@SpringBootTest
class SpringBoot02ConfigApplicationTests {

    @Autowired
    private Person person;

    @Autowired
    ApplicationContext ioc;

    @Test
    public void testHelloService() {
        boolean b = ioc.containsBean("helloService");
        System.out.println(b);
    }

    @Test
    void contextLoads() {
        System.out.println(person);
    }

}
