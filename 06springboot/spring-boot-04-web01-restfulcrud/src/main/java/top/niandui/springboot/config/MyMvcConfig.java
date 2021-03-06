package top.niandui.springboot.config;

import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.niandui.springboot.component.LoginHandlerInterceptor;
import top.niandui.springboot.component.MyLocaleResolver;

/**
 * WebMvc配置类
 * 是：WebMvcConfigurationAdapter 类型，不能标注 @EnableWebMvc
 * 现在是 WebMvcConfigurer 接口
 *
 * *** 使用 WebMvcConfigurer 可以来扩展 SpringMVC 的功能
 * *** 要什么功能，就重写什么方法
 * **** 扩展 SpringMVC 的功能：
 *          既能保留了所有的自动配置，也能用我们扩展的配置
 *      当添加 @EnableWebMvc 时： 全面接管
 *          表示SpringBoot对SpringMVC的自动配置都不要的，所有都是我们自己配；
 */
//@EnableWebMvc
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 添加视图映射
        // 浏览器发送 /niandui 请求，也来到 success 页面
        registry.addViewController("/niandui").setViewName("success");
    }

    // 所有的 WebMvcConfigurer 组件都会一起起作用
    @Bean // 将组件注册到容器中
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {

            // 视图映射器
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                // 首页
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                // 主页
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            // 注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                // 添加登录拦截器，拦截任意请求，排除登录请求
                // 静态资源； *.css , *.js
                // SpringBoot已经做好了静态资源映射
//                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
//                    .excludePathPatterns("/", "/index.html", "/user/login");
            }
        };
    }

    // 将自己的区域信息解析器，添加到容器中，替换自动配置的
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }
}
