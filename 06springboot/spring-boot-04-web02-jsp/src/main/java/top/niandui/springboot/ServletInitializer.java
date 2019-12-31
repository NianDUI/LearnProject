package top.niandui.springboot;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 3、必须编写一个 SpringBootServletInitializer 实现类
 *      并调用 configure 方法
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBoot04Web02JspApplication.class);
    }

}
