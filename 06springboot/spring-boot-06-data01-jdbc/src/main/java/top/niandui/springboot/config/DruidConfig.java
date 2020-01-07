package top.niandui.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Druid数据源配置
 */
@Component
public class DruidConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource") // 使用属性配置
    public DataSource druid() {
        return new DruidDataSource();
    }

    // 配置Druid的监控
    // 1、配置一个管理后台的Servlet

    // 2、配置一个监控的filter
}
