package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.*;

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
 * @Import:
 *      作用：用于导入其他的配置类
 *      属性：
 *          value：用于指定其他的配置类的子节码。
 *                  当我使用@Import的注解之后，有@Import注解的类就是父配置类，而导入的类就是子配置类。
 *
 * @PropertySource:
 *      作用：用于指定properties文件的位置
 *      属性：
 *          value：指定文件的名称和路径。
 *              关键字：classpath，表示类路径下。
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

//@ComponentScan({"top.niandui", "config"}) // 使用该注解时，需要在自配置类中使用@Configuration注解
// 当时用@Import时，JdbcConfig类可以不使用@Configuration和在@ComponentScan中指定要扫描的包
@Import(JdbcConfig.class)

@PropertySource("classpath:jdbcConfig.properties")
public class SpringConfiguration {


}
