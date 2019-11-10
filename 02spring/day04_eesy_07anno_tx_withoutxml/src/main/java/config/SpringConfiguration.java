package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * spring的配置类，相当于bean.xml
 */
@Configuration
@ComponentScan("top.niandui")
@Import({JdbcConfig.class, TransactionConfig.class})
@PropertySource("classpath:jdbcConfig.properties")
@EnableTransactionManagement // 开启注解支持事务
public class SpringConfiguration {

}
