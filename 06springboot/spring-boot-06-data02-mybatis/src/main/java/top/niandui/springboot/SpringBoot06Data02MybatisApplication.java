package top.niandui.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 六、数据访问 3、整合mybatis
 *      MybatisAutoConfiguration
 *      导入 mybatis-spring-boot-starter 的jar包
 *
 *   1、注解版
 *      MyBatisConfig.java      MyBatis配置文件
 *      DepartmentMapper.java   注解版的mapper接口
 *      使用@MapperScan批量扫描所有mapper接口 @MapperScan(basePackages = "top.niandui.springboot.mapper")
 */
@SpringBootApplication
public class SpringBoot06Data02MybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot06Data02MybatisApplication.class, args);
    }

}
