package top.niandui.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 六、数据访问 1、jdbc
 *      DataSourceAutoConfiguration
 *
 * 默认规则
 * 会自动加载自动建表语句 schema-all.sql，schema.sql
 * 也可以指定文件
 *
 *      JdbcTemplateAutoConfiguration
 *          为我们自动注入了：JdbcTemplate 和 NamedParameterJdbcTemplate
 *
 * 2、切换数据源
 * 引入数据源jar包，
 * 引入Druid包需要配置，druid-spring-boot-starter包不需要type配置
 *      配置数据源spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
 * 进行Druid数据源配置DruidConfig.java
 *
 */
@SpringBootApplication
public class SpringBoot06DataJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot06DataJdbcApplication.class, args);
    }

}
