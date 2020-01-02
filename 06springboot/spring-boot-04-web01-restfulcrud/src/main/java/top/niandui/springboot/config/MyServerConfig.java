package top.niandui.springboot.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.niandui.springboot.filter.MyFilter;
import top.niandui.springboot.listener.MyListener;
import top.niandui.springboot.servlet.MyServlet;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import java.util.Arrays;
import java.util.EventListener;

/**
 * 和服务器有关的配置类
 *
 *      ServerProperties.java server配置类
 *
 * 注册服务器三大组件
 *      Servlet : ServletRegistrationBean
 *      Filter : FilterRegistrationBean
 *      Listener : ServletListenerRegistrationBean
 */
@Configuration
public class MyServerConfig {

    /********************* 8 嵌入式Servlet容器配置********************************/
    // 配置嵌入式的Servlet容器，定制器
    // 1.x 使用的是 EmbeddedServletContainerCustomizer . ConfigurableEmbeddedServletContainer
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {

            // 通过该方法，定制嵌入式的Servlet容器相关的规则
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                factory.setPort(8083);
            }
        };
    }

    // 注册服务器三大组件
    @Bean
    public ServletRegistrationBean myServlet() {
        ServletRegistrationBean<Servlet> registrationBean = new ServletRegistrationBean<>(new MyServlet(), "/myServlet");
        // 设置启动顺序
        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MyFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/hello", "/myServlet"));
        return registrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean myListener() {
        ServletListenerRegistrationBean<EventListener> registrationBean = new ServletListenerRegistrationBean<>();
        registrationBean.setListener(new MyListener());
        return registrationBean;
    }
}
