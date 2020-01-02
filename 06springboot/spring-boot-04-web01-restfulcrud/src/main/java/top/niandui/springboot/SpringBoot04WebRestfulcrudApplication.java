package top.niandui.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.format.Formatter;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * WebMvcAutoConfiguration
 *      做WebMvc的自动配置
 *
 * 06：区域信息国际化
 * MessageSourceAutoConfiguration
 *      做国际化组件的自动配置
 *
 * 07：登录和拦截器
 *
 * ErrorMvcAutoConfiguration
 *      mvc错误处理的自动配置
 *
 * 08：Servlet 容器
 *      EmbeddedWebServerFactoryCustomizerAutoConfiguration
 *          嵌入式Web服务工厂定制器自动配置
 *
 *      ServletWebServerFactoryAutoConfiguration
 *          ServletWeb服务工厂自动配置 ↓
 *      ServletWebServerFactoryAutoConfiguration.BeanPostProcessorsRegistrar.class
 *          ：ServletWeb服务工厂自动配置。Bean后置处理器注册器
 *          WebServerFactoryCustomizerBeanPostProcessor
 *              ：Web服务工厂定制器Bean后置处理器
 *              ：bean初始化前后（创建完对象，还没有属性赋值）执行初始化工作
 *              方法：调用顺序
 *                  postProcessBeforeInitialization() ↓
 *                      postProcessBeforeInitialization() ↓
 *                          getCustomizers() ↓
 *                              getWebServerFactoryCustomizerBeans() ↓
 *                                  this.beanFactory.getBeansOfType(WebServerFactoryCustomizer.class, false, false).values();
 *                                  从容器beanFactory中获取，WebServerFactoryCustomizer.class 的。
 *
 *
 *      ServletWebServerFactoryConfiguration
 *          ServletWeb服务工厂配置 ↓
 *          @Bean TomcatServletWebServerFactory TomcatServletWeb服务工厂 ↓
 *              public WebServer getWebServer(ServletContextInitializer... initializers) {}
 *                  方法：创建tomcat容器对象
 *      ServletWebServerFactoryCustomizer
 *          ServletWeb服务工厂定制器
 *          ServerProperties 的设置,在该类中进行设置。
 *
 */
@SpringBootApplication
public class SpringBoot04WebRestfulcrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot04WebRestfulcrudApplication.class, args);
    }


    /**
     * springboot自动配置类，会获取容器中所有的视图解析器，
     *      WebMvcAutoConfiguration.viewResolver()
     *
     * 向spring容器中添加一个视图解析器
     * 可以debug中DispatcherServlet.doDispatch()打断点，
     * 查看this.viewResolvers.0.viewResolvers 属性查看是否有自己的视图解析器
     */
//    @Bean
    public ViewResolver myViewResolver() {
        return new MyViewResolver();
    }

    private static class MyViewResolver implements ViewResolver {

        @Override
        public View resolveViewName(String viewName, Locale locale) throws Exception {
            return null;
        }
    }

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    // 添加一个自己的格式化转化器
//    @Bean
    public Formatter<Date> myDateFormatter() {
        return new Formatter<Date>() {

            @Override
            public Date parse(String text, Locale locale) throws ParseException {
                return sdf.parse(text);
            }

            @Override
            public String print(Date object, Locale locale) {
                return sdf.format(object);
            }
        };
    }

    // 添加一个自己的格式化转化器
    /*@Bean
    public Formatter<Timestamp> myTimestampFormatter() {
        return new Formatter<Timestamp>() {
            @Override
            public Timestamp parse(String text, Locale locale) throws ParseException {
                return Timestamp.valueOf();
            }

            @Override
            public String print(Timestamp object, Locale locale) {
                return object.toString();
            }
        };
    }*/

}
