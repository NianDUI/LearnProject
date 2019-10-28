package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * 该类是一个配置类，他的作用和bean.xml是一样的
 *
 * spring中的新注解
 * @Configuration
 *      作用：指定当前类是一个配置类
 *      细节：当配置类作为AnnotationConfigApplicationContext对象创建的参数时，该注解可以不写。
 *
 * @ComponentScan
 *      作用：用于通过注解指定spring在创建容器时要扫描的包
 *      属性：
 *          value：它和basePackages的作用是一样的，都是用于指定在创建容器时要扫描的包。
 *                 我们使用此注解就等同于在xml配置了：
 *                      <context:component-scan base-package="top.niandui"></context:component-scan>
 *
 * @Bean：
 *      作用：用于把当方法的返回值作为bean对象存入spring的ioc容器中
 *      属性：
 *          name：用于指定bean的id。当不写时，默认值是当前方法的名称。
 *      细节：
 *          当我们使用注解配置方法时，如果方法有参数，spring框架会去容器中查找有没有可用的bean对象
 *          查找的方式和Autowired注解的作用是一样的
 *
 */
@Configuration
/**
 *     <!-- 告知spring在创建容器时要扫描的包，配置所需要的标签不是在beans的约束中，
 *             而是一个名为 context 名称空间和约束中
 *     -->
 *     <context:component-scan base-package="top.niandui"></context:component-scan>
 */
@ComponentScan("top.niandui")
//@ComponentScan(value = "top.niandui")
//@ComponentScan(basePackages = "top.niandui")
public class SpringConfiguration {

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
            ds.setDriverClass("com.mysql.jdbc.Driver");
            ds.setJdbcUrl("jdbc:mysql://localhost:3306/eesy");
            ds.setUser("root");
            ds.setPassword("root");
            return ds;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
