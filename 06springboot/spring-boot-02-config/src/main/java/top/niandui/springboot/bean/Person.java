package top.niandui.springboot.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
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
 */
@Component
//@ConfigurationProperties(prefix = "person")
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
