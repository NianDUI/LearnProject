package top.niandui.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 六、数据访问 4、整合jpa
 * 导包：
 * 配置文件：
 *      数据源配置
 *      jpa配置: JpaProperties有绑定
 * 实体类：
 *      使用JPA注解配置映射关系
 * 操作实体类对应数据表的接口：
 *      继承JpaRepository(Repository)来完成对数据库的操作
 *          JpaRepository<T, ID> T: 要操作那个实体类，ID: 实体类主键的类型
 */
@SpringBootApplication
public class SpringBoot06Data03JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot06Data03JpaApplication.class, args);
    }

}
