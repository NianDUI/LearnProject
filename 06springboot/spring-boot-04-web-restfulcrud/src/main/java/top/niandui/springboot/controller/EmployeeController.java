package top.niandui.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.niandui.springboot.EmployeeDao;
import top.niandui.springboot.entities.Employee;

import java.util.Collection;
import java.util.Map;

/**
 * 员工管理
 */
@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    // 查询所有员工返回列表页面
    @GetMapping("/emps")
    public String List(Model model) {
        Collection<Employee> employees = employeeDao.getAll();

        // 放在请求域中
        model.addAttribute("emps", employees);

        // thymeleaf 默认就会拼串
        // classpath:/templates/xxxx.html
        return "emp/list";
    }

    // 来到员工添加页面
    @GetMapping("/emp")
    public String toAddPage() {
        // 来到添加页面，查出所有的部门，在页面显示
        return "emp/add";
    }

    // 添加员工
    // SpringMVC自动将请求参数和入参对象的属相进行一一绑定；
    //      要求请求参数的名字和JavaBean入参对象里面的属性名是一样的
    @PostMapping("/emp")
    public String addEmp(Employee employee) {

        System.out.println("保存的员工信息" + employee);
        employeeDao.save(employee);

        // 来到员工列表页面
        // redirect: 重定向到一个地址, /代表当前项目的地址
        // forword: 转发到一个地址
        return "redirect:/emps";
    }

    // 来到修改页面，查出当前员工，在页面回显
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable Integer id, Model model) {
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);

        // 页面要显示所有的部门列表

        // 回到修改页面(add是一个修改添加二合一的页面)
        return "emp/add";
    }

    // 员工修改
    @PutMapping("/emp")
    public String updateEmployee(Employee employee) {
        System.out.println("修改的员工信息" + employee);
        employeeDao.save(employee);

        return "redirect:/emps";
    }

    // 员工删除
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
