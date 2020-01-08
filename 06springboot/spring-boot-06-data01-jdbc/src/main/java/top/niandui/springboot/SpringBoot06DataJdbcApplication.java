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
 * 引入druid包需要配置，druid-spring-boot-starter包不需要type配置
 * 在application.yml中进行数据源的额外配置，可不配置。
 *
 */
@SpringBootApplication
public class SpringBoot06DataJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot06DataJdbcApplication.class, args);
    }

}
