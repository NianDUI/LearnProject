package top.niandui.springboot.config;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Title: MyBatisConfig.java
 * @description: MyBatis配置文件
 * @time: 2020/1/8 22:03
 * @author: liyongda
 * @version: 1.0
 */
// mybatis 映射包扫描，相当于给该包下的每一个类都加上了一个@Mapper注解
@MapperScan(basePackages = "top.niandui.springboot.mapper")
@Configuration
public class MyBatisConfig {

    // 自定义mybatis的配置规则; 给容器中添加一个ConfigurationCustomizer组件;
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        // 配置定制器，在 MybatisAutoConfiguration 中获取并设置想
        return new ConfigurationCustomizer() {
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                // 开启驼峰命名法映射规则
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}
