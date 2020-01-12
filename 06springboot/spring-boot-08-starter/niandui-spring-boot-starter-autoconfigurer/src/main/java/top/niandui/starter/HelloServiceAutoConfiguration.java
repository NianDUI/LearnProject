package top.niandui.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * HelloServiceAutoConfiguration
 *
 * 步骤：
 *      1、写好自动配置类和其他配置
 *      2、在resources路径下的 META-INF/spring.factories 文件中加入
 *          org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
 *          自己的自动配置类路径(top.niandui.starter.HelloServiceAutoConfiguration)
 *      3、自动配置和启动器安装到maven
 *      4、引用启动器使用即可
 */
@Configuration
@ConditionalOnWebApplication // web应用才生效
@EnableConfigurationProperties({HelloProperties.class}) // 让属性生效，就可以直接在类里面使用
public class HelloServiceAutoConfiguration {

    @Autowired
    HelloProperties helloProperties;

    @Bean
    public HelloService helloService() {
        HelloService service = new HelloService();
        service.helloProperties = helloProperties;
        return service;
    }
}
