package top.niandui.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 四、Web开发 9、使用外置的Servlet容器
 *
 * 1、 创建一个war项目
 * 2、pom
 * 3、ServletInitializer
 * 4、启动容器就可以了
 */
@SpringBootApplication
public class SpringBoot04Web02JspApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot04Web02JspApplication.class, args);
    }

}
