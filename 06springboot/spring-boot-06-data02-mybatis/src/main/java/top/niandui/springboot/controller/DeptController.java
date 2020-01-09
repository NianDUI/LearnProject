package top.niandui.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.niandui.springboot.bean.Department;
import top.niandui.springboot.bean.Employee;
import top.niandui.springboot.mapper.DepartmentMapper;
import top.niandui.springboot.mapper.EmployeeMapper;

/**
 * @Title: DeptController.java
 * @description: DeptController
 * @time: 2020/1/8 21:49
 * @author: liyongda
 * @version: 1.0
 */
@RestController
public class DeptController {
    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @GetMapping("/dept/{id}")
    public Department getDepartment(@PathVariable Integer id) {
        return departmentMapper.getDeptById(id);
    }

    @GetMapping("/dept")
    public Department insertDept(Department department) {
        departmentMapper.insertDept(department);
        return department;
    }

    @GetMapping("/emp/{id}")
    public Employee getEmp(@PathVariable Integer id) {
        return employeeMapper.getEmpById(id);
    }
}
