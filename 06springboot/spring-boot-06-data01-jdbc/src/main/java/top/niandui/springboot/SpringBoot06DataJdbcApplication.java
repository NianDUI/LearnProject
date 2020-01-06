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
 */
@SpringBootApplication
public class SpringBoot06DataJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot06DataJdbcApplication.class, args);
    }

}
