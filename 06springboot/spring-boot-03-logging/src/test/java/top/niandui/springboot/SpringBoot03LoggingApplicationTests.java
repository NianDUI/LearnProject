package top.niandui.springboot;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBoot03LoggingApplicationTests {

    // 日志记录器
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    void contextLoads() {
//        System.out.println();

        // 日志的级别
        // 由低到高     trace < debug < info < warn < error
        // 可以调整需要输出的日志级别；日志就只会在这个级别以以后的高级别生效
        logger.trace("这是trace日志..."); // trace 跟踪
        logger.debug("这是debug日志...");
        // SpringBoot 默认给我们使用的是Info级别的，没有指定级别的就用SpringBoot默认规定的级别；root级别
        logger.info("这是info日志...");
        logger.warn("这是warn日志..."); // warn 警告
        logger.error("这是error日志...");
        
    }

}
