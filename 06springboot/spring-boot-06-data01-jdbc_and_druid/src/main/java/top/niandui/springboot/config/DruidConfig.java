package top.niandui.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Druid数据源配置
 * 如果在配置文件中配置了的话这些都不需要配置
 */
@Component
public class DruidConfig {

//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource") // 使用属性配置
//    public DataSource druid() {
//        return new DruidDataSource();
//    }
//
//    // 配置Druid的监控
//    // 1、配置一个管理后台的Servlet
//    @Bean
//    public ServletRegistrationBean statViewServlet() {
//        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
//
//        Map<String, String> initParams = new HashMap<>();
//        initParams.put("loginUsername", "admin");
//        initParams.put("loginPassword", "admin");
//        initParams.put("allow", ""); // 默认允许所有访问
//        initParams.put("deny", "127.0.0.1");
//        bean.setInitParameters(initParams);
//        return bean;
//    }
//
//    // 2、配置一个监控的filter
//    @Bean
//    public FilterRegistrationBean webStatFilter() {
//        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>();
//        bean.setFilter(new WebStatFilter());
//        bean.setUrlPatterns(Arrays.asList("/*"));
//        Map<String, String> initParams = new HashMap<>();
//        initParams.put("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
//        bean.setInitParameters(initParams);
//        return bean;
//    }
}
