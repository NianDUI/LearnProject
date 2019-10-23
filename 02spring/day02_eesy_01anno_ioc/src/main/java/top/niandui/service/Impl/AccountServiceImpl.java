package top.niandui.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import top.niandui.dao.IAccountDao;
import top.niandui.dao.impl.AccountDaoImpl;
import top.niandui.service.IAccountService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * 账户的业务实现类
 *
 * 曾经xml的配置:
 *      <bean id="accountService" class="top.niandui.service.Impl.AccountServiceImpl"
 *          scoper="" init-method="" destroy-method="" >
 *          <property name="" value="" | ref=""></property>
 *      </bean>
 *
 * 四类注解：（11个）
 * 1、 用于创建对象的注解
 *      他们的作用就和在xml配置文件中编写一个<bean>标签实现的功能是一样的
 *      @Component:
 *          作用：用于把当前类的对象存入spring容器中
 *          属性：
 *              value：用于指定bean的id。当我们不写时，它的默认值是当前类名，且首字母小写。
 *      @Controller ：一般用在表现层
 *      @Service    ：一般用在业务层
 *      @Repository ：一般用在持久层
 *      以上三个注解他们的作用和属性与Component是一摸一样的。
 *      他们三个是spring框架为我们提供明确的三层使用，使我们的三层对象更加清晰
 *
 * 2、 用于注入数据的注解
 *      他们的作用就和在xml配置文件中bean标签中写一个<property>标签作用是一样的
 *      @Autowired:
 *          作用：自动按照类型注入。只要容器中有唯一的一个bean对象类型和要注入的变量匹配，就可以注入成功
 *                  如果ioc容器中没有任何bean的类型和要注入的变狼类型匹配，则报错。
 *                  如果Ioc容器中有多个类型匹配时：
 *                      先根据类型圈定出来匹配的对象，再使用变量名称作为bean的id，在圈定出来的变量中找，有匹配的注入，没有则报错
 *          出现位置：
 *              可以变量上，也可以是方法上
 *          细节：在使用注解注入时，set方法就不是必须的了。
 *      @Qualifier：
 *          作用：再按照类型注入的基础上再按照名称注入。
 *                  他在给类成员注入时不能单独使用（和Autowire配合）。但是在给方法参数注入时可以（稍后讲解）
 *          属性：
 *              value：用于指定注入bean的id
 *
 *      @Resource：
 *          作用：直接按照bean的id注入。他可以单独使用。
 *          属性：
 *              name：用于指定bean的id。
 *      以上三个注解都能注入其他bean类型的数据，而基本类型和String类型无法使用上述注解实现。
 *      另外，集合类型的注入只能通过xml来实现。
 *
 *      @Value：
 *          作用：用于注入基本数据类型和String类型的数据。
 *          属性：
 *              value：用于指定数据的值。他可是使用spring中的SpEL（也就是spring的el表达式）
 *                          SpEL的写法：${表达式 }
 *
 * 3、 用于改变作用范围的注解
 *      他们的作用就和在bean标签中使用scope属性实现的功能是一样的
 *      @Scope：
 *          作用：用于指定bean的作用范围
 *          属性：
 *              value：指定范围的取值。常用取值：singleton（默认） prototype
 *
 * 4、 和生命周期相关的注解
 *      他们的作用就和在bean标签中使用init-method和destroy-method属性实现的功能是一样的
 *      @PostConstruct
 *          作用：用于指定初始化方法
 *      @PreDestroy
 *          作用：用于指定销毁方法
 *
 */
//@Component("accountService")
//@Component(value="accountService")
//@Controller("accountService")
@Service("accountService")
@Scope("singleton")
//@Scope("prototype")
public class AccountServiceImpl implements IAccountService {

    // 方式一
//    @Autowired
    // 方拾二
//    @Autowired
//    @Qualifier("accountDao1")
    // 方式三
    @Resource()
//    @Resource(name="accountDao2")
    private IAccountDao accountDao2;

    public void saveAccount() {
        accountDao2.saveAccount();
    }

    @PostConstruct
    public void inti() {
        System.out.println("初始化方法执行了");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("销毁方法执行了");
    }

}
