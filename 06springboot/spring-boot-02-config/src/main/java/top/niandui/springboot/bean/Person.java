package top.niandui.springboot.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 将配置文件中配置的每一个属性的值，映射到这个组件中
 * 使用：
 * 第一中获取值得方式：
 *      @ConfigurationProperties 注解：
 *          告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定；
 *          prefix = "person": 配置文件中那个下面的的所有属性进行一一映射
 *
 *      注：只有这个组件是容器中的组件，才能使用容器提供的功能
 *
 * 第二种获取值得方式
 *      @Value
 *
 * 二、区别：
 *                      @ConfigurationProperties            @Value
 * 功能                 批量注入文件中的属性                  一个个指定
 * 松散绑定(松散语法)    支持                                 不支持
 * SpEL                 不支持                               支持
 * JSR303数据校验        支持                                 不支持
 * 复杂类型封装          支持                                 不支持
 *
 * 配置文件yaml还是properties他们都能获取到值；
 *
 * 总结：
 *      如果说，我们只是在某个业务逻辑中获取一下配置文件中的某项值，使用@Value
 *      如果说，我们专门编写了一个JavaBean来和配置文件进行映射，我们就直接使用@ConfigurationProperties
 *
 * 三、配置文件注入值数据校验
 *      必须使用@ConfigurationProperties
 *
 *
 * @ConfigurationProperties 默认从全局配置文件中获取值；想要从起他配置文件获取怎么办
 * 四、给容器中加载配置文件 @PropertySource & @ImportResource
 *      1、@PropertySource: 加载指定的配置文件；
 *
 *      2、@ImportResource: 导入spring的配置文件，让配置文件里面的内容生效；
 *          Spring Boot里面默认没有spring的配置文件，我们自己编写的配置文件，已不能自动识别；
 *          想让Spring的配置文件生效，加载进来；将 @ImportResource 标注在一个配置类上
 *              @ImportResource(locations = {"classpath:bean.xml"})  // 加载指定的Spring配置文件
 *
 *              <bean id="helloService" class="top.niandui.springboot.service.HelloService"/>
 *
 *      3、SpringBoot推荐给容器添加组件的方式
 *          1）、配置类======类似于Spring配置文件
 *              @Configuration
 *
 * 五、配置文件占位符
 *      ${xxx.xx:默认值}
 *      1、随机数
 *          ${random.int}、${random.long}、${random.value}、
 *          ${random.int(10)}、${random.int[1024,2048]}、${random.uuid}
 *
 *      2、占位符获取之前的值，如果没有可以使用:指定默认值
 *          ${person.hello:hello}
 *
 * 六、Profile：多环境支持
 *      1、多Profile文件
 *          我们在主配置文件编写的时候，文件名可以是 application-{profile}.properties/yml
 *
 *          默认使用 application.properties/yml 的配置;
 *
 *          {profile} 可以自定义配置
 *
 *      2、yaml支持多文档块方式
 *
 *      “---”： 来分割不同的文档块
 *      “spring.profiles=dev”： 来制定文档块的名称
 *
 *      3、激活指定profile
 *          1）、在配置文件中指定激活那个配置
 *              spring.profiles.active=dev
 *          2）、命令行：     （在运行的时候指定的配置文件）
 *              java -jar spring-boot-02-config-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
 *              可以在测试的时候，配置传入命令行参数；
 *          3）、虚拟机参数
 *              -Dspring.profiles.active=dev (-Dxxx=xx:规范)
 *
 * 七、配置文件记载位置
 *      1、默认会扫描的位置
 *          file：./config   (.:项目根目录)
 *          file: ./
 *          classpath:/config
 *          classpath:/
 *          注：
 *              优先级从高到底，高优先级的配置会覆盖底优先级的配置
 *      2、我们也可以通过spring.config.location来改变默认配置
 *
 */
//@PropertySource(value = {"classpath:person.properties"}) // 加载指定的Property配置文件
@Component
@ConfigurationProperties(prefix = "person")
//@Validated // 用于数据校验
public class Person {

    /**
     * <bean class="Person">
     *      <property name="lastName" value="字面量/${key}从环境变量、配置文件中获取值/#{SpEL}"></property>
     * </bean>
     */
    // lastName 必须是邮箱格式
//    @Email
//    @Value(value = "${person.last-name}")
    private String lastName;
//    @Value("#{11 * 2}")
    private Integer age;
//    @Value("true")
    private Boolean boss;
    private Date birth;

//    @Value("${person.maps}") // 错误，@Value处理不了复杂类型
    private Map<String, Object> maps;
    private List<Object> lists;

    private Dog dog;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getBoss() {
        return boss;
    }

    public void setBoss(Boolean boss) {
        this.boss = boss;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public List<Object> getLists() {
        return lists;
    }

    public void setLists(List<Object> lists) {
        this.lists = lists;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "Person{" +
                "lastName='" + lastName + '\'' +
                ", age=" + age +
                ", boss=" + boss +
                ", birth=" + birth +
                ", maps=" + maps +
                ", lists=" + lists +
                ", dog=" + dog +
                '}';
    }
}
