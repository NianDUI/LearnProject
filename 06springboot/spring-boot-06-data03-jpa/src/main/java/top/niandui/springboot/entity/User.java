package top.niandui.springboot.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Title: User.java
 * @description: TODO
 * @time: 2020/1/9 21:36
 * @author: liyongda
 * @version: 1.0
 */
@Data
// 使用JPA注解配置映射关系
@Entity // 告诉JPA这是一个实体类(和数据表映射的类)
@Table(name = "tbl_user") // 指定和那个数据表进行映射, 如果省略默认表明就是user , 没有会自动创建
public class User {
    @Id // 指定这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增主键，strategy主键生成策略
    private Integer id;

    @Column(name = "last_name", length = 50) // 指定这是和数据表对应的一个列，并指定一些属性
    private String lastName;

    @Column // 省略默认列名就是属性名
    private String email;

}
