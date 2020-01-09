package top.niandui.springboot.bean;

import lombok.Data;

/**
 * @Title: Employee.java
 * @description: Employee
 * @time: 2020/1/8 21:37
 * @author: liyongda
 * @version: 1.0
 */
@Data
public class Employee {
    private Integer id;
    private String lastName;
    private String email;
    private Integer gender;
    private Integer dId;
}
