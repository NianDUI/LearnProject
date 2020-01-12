package top.niandui.springboot.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.HashMap;

/**
 * Spring应用运行监听器
 *      需要配置在META-INF/spring.factories中
 * HelloSpringApplicationRunListener
 *      实现 SpringApplicationRunListener 接口
 *
 * ConfigurableApplicationContext：ioc容器
 */
public class HelloSpringApplicationRunListener implements SpringApplicationRunListener {

    public HelloSpringApplicationRunListener(SpringApplication application, String[] args) {

    }

    @Override
    public void starting() {
        System.out.println("SpringApplicationRunListener...starting...开始");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        System.out.println("系统名称" + environment.getSystemProperties().get("os.name"));
        System.out.println("SpringApplicationRunListener...environmentPrepared...环境准备好了");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println("SpringApplicationRunListener...contextPrepared...ioc容器上下文准备好了");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println("SpringApplicationRunListener...contextLoaded...ioc容器上下文加载完成");
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        System.out.println("SpringApplicationRunListener...started...启动");
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        System.out.println("SpringApplicationRunListener...running...运行");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println("SpringApplicationRunListener...failed...失败");
    }
}
