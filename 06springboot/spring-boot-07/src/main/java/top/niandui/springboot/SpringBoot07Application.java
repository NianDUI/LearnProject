package top.niandui.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 七、SpringBoot启动配置原理
 *      1、创建SpringApplication对象
 *      2、运行run方法
 *      3、事件监听机制
 *          配置在META-INF/spring.factories
 *          ApplicationContextInitializer
 *          SpringApplicationRunListener
 *
 *          只需要放在ioc容器中
 *          ApplicationRunner
 *          CommandLineRunner
 *
 * 项目
 *      listener包：
 */
@SpringBootApplication
public class SpringBoot07Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot07Application.class, args);
    }

}
