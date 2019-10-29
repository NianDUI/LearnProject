package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;

/**
 * 和spring链接数据库相关的配置类
// */
//@Configuration
public class JdbcConfig {

    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    /**
     * 用于创建一个QueryRunner对象
     * @param dataSource
         * @return
         *     <!-- 配置QueryRunner
         *         scope="prototype" 保证每次使用都是创建一个新对象
         *     -->
         *     <bean id="runner" class="org.apache.commons.dbutils.QueryRunner" scope="prototype">
         *         <!-- 注入数据源 -->
         *         <constructor-arg name="ds" ref="dataSource"></constructor-arg>
     *     </bean>
     */
    @Bean(name = "runner")
    @Scope("prototype") // 设置bean对象的作用域（单例还是多例）
    public QueryRunner createQueryRunner(DataSource dataSource) {
        return new QueryRunner(dataSource);
    }

    /**
     * 创建数据源对象
     * @return
     *     <!-- 配置数据源 -->
     *     <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
     *         <!-- 链接数据库的必备信息 -->
     *         <property name="driverClass" value="com.mysql.jdbc.Driver"></property>
     *         <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/eesy"></property>
     *         <property name="user" value="root"></property>
     *         <property name="password" value="root"></property>
     *      </bean>
     */
    @Bean(name = "dataSource")
    public DataSource createDataSource() {
        try {
            ComboPooledDataSource ds = new ComboPooledDataSource();
            ds.setDriverClass(driver);
            ds.setJdbcUrl(url);
            ds.setUser(username);
            ds.setPassword(password);
            return ds;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
