package top.niandui.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *      配置文件能配置的属性参照
 *      https://docs.spring.io/spring-boot/docs/1.5.9.RELEASE/reference/html/common-application-properties.html
 * 九、自动配置的原理
 *      配置文件到底能写什么？怎么写？自动配置原理；
 *
 *      *自动配置原理：
 *          1）、SpringBoot启动的时候加载主配置类，开启了自动配置功能@EnableAutoConfiguration
 *          2）、@EnableAutoConfiguration 作用：
 *              1.利用AutoConfigurationImportSelector给容器中导入一些组件？
 *              2.可以查看selectImports()方法的内容；
 *              3.List<String> configurations = getCandidateConfigurations(annotationMetadata, attributes);获取候选的配置
 *                  SpringFactoriesLoader.loadFactoryNames()
 *                      扫描所有jar包类路径下的 META-INF/spring.factories 文件，
 *                      把扫描到的这些文件的内容包装成properties对象
 *                      从properties中获取到EnableAutoConfiguration.class类（类名）对应的值，然后把它们添加到容器中
 *                  将类路径下的 META-INF/spring.factories 里面配置的所有 EnableAutoConfiguration 的值加入到了容器中
 *                      spring-boot-autoconfigure-2.2.1.RELEASE.jar!/META-INF/spring.factories
 *                          # Auto Configure
 *                          org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
 *                  每一个这样的 xxxAutoConfiguration 类都是容器中的一个组件，都加入到容器中；
 *                      作用：用他们做自动配置
 *           3）、每一个自动配置类进行自动配置功能
 *           4）、以HttpEncodingAutoConfiguration（Http编码自动配置）为例解释自动配置原理；
 *              @Configuration(proxyBeanMethods = false)
 *                  表示这是一个配置类，以前编写的配置文件一样，也可以给容器中添加组件
 *              @EnableConfigurationProperties(HttpProperties.class)
 *                  启动指定类的ConfigurationProperties功能；将配置文件中对应的值和HttpProperties绑定起来；
 *                  并把HttpProperties加入到ioc容器中
 *              @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
 *                  Spring底层@Conditional注解（Spring注解版原理）：根据不同的条件，如果满足指定的条件，整个配置类里面的配置就会生效；
 *                  判断当前应用是否是web应用，如果是，当前配置类生效
 *              @ConditionalOnClass(CharacterEncodingFilter.class)
 *                  判断当前项目有没有这个类
 *                  CharacterEncodingFilter：SpringMVC中进行乱码解决的过滤器；
 *              @ConditionalOnProperty(prefix = "spring.http.encoding", value = "enabled", matchIfMissing = true)
 *                  判断配置文件中是否存在某个配置 spring.http.encoding.enabled；
 *                  matchIfMissing = true：如果不存在判断也是成立的
 *                  即使我们配置文件中不配置spring.http.encoding.enabled=true，也是默认生效的
 *              public class HttpEncodingAutoConfiguration {
 *                  // 他已经和SpringBoot的配置文件映射了
 *                  private final HttpProperties.Encoding properties;
 *
 *                  // 只有一个有参构造器的情况下，参数的值就会从容器中拿@EnableConfigurationProperties
 *                  public HttpEncodingAutoConfiguration(HttpProperties properties) {
 * 		                this.properties = properties.getEncoding();
 *                  }
 *
 *                  @Bean // 给容器中添加一个组件，这个组建的某些之需要从properties中获取
 *                  @ConditionalOnMissingBean
 *                  public CharacterEncodingFilter characterEncodingFilter() {
 * 		                CharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
 * 		                filter.setEncoding(this.properties.getCharset().name());
 * 		                filter.setForceRequestEncoding(this.properties.shouldForce(Type.REQUEST));
 * 		                filter.setForceResponseEncoding(this.properties.shouldForce(Type.RESPONSE));
 * 		                return filter;
 *                  }
 *              }
 *
 *              自动配置类一句话解释：
 *                  根据当前不同的条件判断，决定这个配置类是否生效；
 *                  一但这个配置类生效；这个配置就会给容器中添加各种组件；
 *                      这些组件的属性是从对应的 xxxProperties类中获取的，这些类里面的每一个属性又是和配置文件绑定的；
 *
 *
 *          5）、所有在配置文件中能配置的属性都是在xxxProperties类中封装者；
 *              配置文件能配置什么就可以参照某一个功能对应的这个属性类
 *              @ConfigurationProperties(prefix = "spring.http")
 *                  从配置文件中获取指定的值和bean的属性进行绑定
 *              public class HttpProperties {
 *                  public static class Encoding {
 *                      public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
 *                  }
 *              }
 *
 *              我们能配那些都是来源于这些xxxProperties类的属性
 *
 *
 *  十、SpringBoot的精髓：
 *      1）、SpringBoot启动会加载大量的自动配置类
 *      2）、我们看我们需要的功能有没有SpringBoot默认写好的自动配置类；
 *      3）、我们再来看这个自动配置中到底配置了那些组件；（只要我们要用的组件有，我们就不需要再来配置了）
 *      4）、给容器中自动配置添加组件的时候，会从xxxProperties类中获取某些属性，我们就可以在配置文件中指定这些属性的值
 *
 *      @EnableConfigurationProperties(xxxProperties.class)
 *      xxxAutoConfiguration ： 自动配置的类；
 *          给容器中添加组件
 *      @ConfigurationProperties(prefix = "spring.xxx")
 *      xxxProperties ：封装配置文件中相关的属性；
 *
 *
 *
 *
 * 十一、细节
 *      1、@Conditional 派生注解（Spring注解版，原生的@Conditional作用）
 *          作用：必须是@Condition指定的条件成立，才给容器中添加组件，配置类里面的所有内容才生效；
 *      2、@Conditional 扩展（有很多）
 *          @ConditionalOnJava ：系统的Java版本是否符合要求
 *          @ConditionalOnBean ：容器中存在指定Bean
 *          @ConditionalOnMissingBean ：容器中不存在指定Bean
 *          @ConditionalOnExpression ：满足SpEl表达式指定
 *          @ConditionalOnClass ：系统之有指定的类
 *          @ConditionalOnMissingClass ：系统之没有指定的类
 *          @ConditionalOnSingleCandidate ：容器中只有一个指定的Bean，或者这个Bean是首选Bean
 *          @ConditionalOnPropety ：系统之指定的属性是否有指定的值
 *          @ConditionalOnResource ：类路径下是否存在指定资源文件
 *          @ConditionalOnWebApplication ：当前环境是Web环境
 *          @ConditionalOnNotWebApplication ：当前环境不是Web环境
 *          @ConditionalOnJndi ：JNDI存在指定项
 *
 *      自动配置类必须在一定的条件下才能生效；
 *          我们怎么指导那些自动配置类生效；
 *          我们可以启用 debug=true属性；来让控制台打印自动而配置报告，这样我们就可以很方便的知道那些自动配置类生效；
 *
 *          application.properties文件中
 *          # 开启SpringBoot的debug模式，控制台打印自动而配置报告
 *          debug=true
 *
 *          自动配置报告
 *          ============================
 *          CONDITIONS EVALUATION REPORT
 *          ============================
 *
 *          Positive matches（自动配置启用的）:
 *          -----------------
 *          DispatcherServletAutoConfiguration matched:
 *              - @ConditionalOnClass found required class 'org.springframework.web.servlet.DispatcherServlet' (OnClassCondition)
 *              - found 'session' scope (OnWebApplicationCondition)
 *
 *          Negative matches（没有启用，没有匹配成功的自动配置类）:
 *          -----------------
 *          ActiveMQAutoConfiguration:
 *              Did not match:
 *                  - @ConditionalOnClass did not find required class 'javax.jms.ConnectionFactory' (OnClassCondition)
 *
 *
 *
 */
@SpringBootApplication
public class SpringBoot02Autoconfig03Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot02Autoconfig03Application.class, args);
    }

}
