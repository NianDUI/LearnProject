package top.niandui.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * resources/static：保存所有的静态资源；
 *      js css images
 * resources/templates：保存所有的摸板页面；
 *      (Spring Boot默认jar包使用嵌入式Tomcat，默认不支持JSP页面)；但是可已使用摸板引擎(freemarker、thymeleaf)；
 * resources/application.properties：Spring Boot 应用的配置文件；
 *      可以修改一些默认设置
 *
 * 配置文件
 *      Spring Boot使用一个全局的配置文件，配置文件名是固定的；
 *          application.properties
 *          application.yml
 *      作用：
 *          修改SpringBoot自动配置的默认值；
 *      SpringBoot在底层都给我们自动配置好了；
 *
 *      YAML（YAML Ain't Markup Language）
 *          YAML A Markup Language：是一个标记语言
 *          YAML isn't Markup Language：不是一个标记语言
 *          YAML: 以数据为中心，比json和xml更适合做配置文件    www.yaml.org
 *                  相同的配置
 *
 *      标记语言：
 *          以前的配置文件；大多都使用的是 xxxx.xml文件；
 */
@SpringBootApplication
public class SpringBoot01HelloworldQuickApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot01HelloworldQuickApplication.class, args);
    }

}
