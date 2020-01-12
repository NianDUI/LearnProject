package top.niandui.springboot.listener;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 应用上下文初始化器
 *      需要配置在META-INF/spring.factories中
 * HelloApplicationContextInitializer
 *      实现 ApplicationContextInitializer接口 泛型为初始化对象
 *
 * ConfigurableApplicationContext：我们的容器(ioc)，
 */
public class HelloApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

     /**
     * 上下文，初始化方法，只要一运行就会调用
     * 在准备上下文环境的时候调用
     * SpringApplication.run().prepareContext().applyInitializers().initialize(context);
     * @param applicationContext
     */
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("ApplicationContextInitializer...initialize..." + applicationContext);
    }
}
