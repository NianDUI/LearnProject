package top.niandui.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * 七、配置文件加载位置
 *      1、默认会扫描的位置
 *          file：./config   (.:项目根目录不是模块路径)
 *          file: ./
 *          classpath:/config
 *          classpath:/
 *          注：
 *              优先级从高到底，高优先级的配置会覆盖底优先级的配置
 *          SpringBoot会从这四个位置全部加载主配置文件；互补配置；
 *
 *      2、我们也可以通过spring.config.location来改变默认配置
 *          在配置文件不起作用：
 *          项目打包好后，我们可以使用命令行参数的形式，启动项目的时候来指定配置文件的新位置；
 *              指定配置文件和默认加载的这些配置文件，会共同起作用形成互补配置；
 *          pwd: target
 *          java -jar spring-boot-02-config-02-0.0.1-SNAPSHOT.jar --spring.config.location=application.properties
 *
 *          新版??? spring.config.additional-location
 *
 *
 * 八、外部配置的加载顺序
 *  SpringBoot也可以从以下位置加载配置；优先级从高到底；高优先级的配置覆盖底优先级的配置，所有配置会形成互补配置
 *      *1、命令行参数
 *          java -jar spring-boot-02-config-02-0.0.1-SNAPSHOT.jar --server.port=8087 --server.servlet.context-path=/abc
 *          多个配置用空格分开：--配置项=值
 *      2、来自java：comp/env的JNDI属性
 *      3、java系统属性（System.getProperties()）
 *      4、操作系统环境变量
 *      5、RandomValuePropertySource配置的random.*属性
 *
 *      (都是由jar外向jar包内寻找；)  若指定了profile：
 *      优先加载带profile
 *      *6、jar包外部application-{profile}.properties或application.yml(带spring.profiles)配置文件
 *      *7、jar包内部application-{profile}.properties或application.yml(带spring.profiles)配置文件
 *
 *      再来加载不带profile
 *      *8、jar包外部application.properties或application.yml(不带spring.profiles)配置文件
 *      *9、jar包内部application.properties或application.yml(不带spring.profiles)配置文件
 *
 *      10、@Configuration注解类上的@PropertySource
 *      11、通过SpringApplication.setDefaultProperites指定的默认属性
 *      所有支持的配置加载来源：参考官方文档
 *      https://docs.spring.io/spring-boot/docs/1.5.9.RELEASE/reference/htmlsingle/#boot-features-external-config
 *
 *
 *
 */
@SpringBootApplication
public class SpringBoot02Config02Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot02Config02Application.class, args);
    }

}
