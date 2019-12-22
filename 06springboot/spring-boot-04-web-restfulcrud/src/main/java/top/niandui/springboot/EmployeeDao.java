package top.niandui.springboot;

import org.springframework.stereotype.Repository;
import top.niandui.springboot.entities.Employee;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 员工dao
 */
@Repository
public class EmployeeDao {

    private static Map<Integer, Employee> employees = null;


    static {
        employees = new HashMap<>();

        employees.put(1001, new Employee(1001, "E-AA", "aa@qq.com", 1, new Date()));
        employees.put(1002, new Employee(1002, "E-BB", "bb@qq.com", 0, new Date()));
        employees.put(1003, new Employee(1003, "E-CC", "cc@qq.com", 0, new Date()));
        employees.put(1004, new Employee(1004, "E-DD", "dd@qq.com", 1, new Date()));
        employees.put(1005, new Employee(1005, "E-EE", "ee@qq.com", 1, new Date()));

    }

    private static Integer initId = 1006;

    public void save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initId++);
        }

        employees.put(employee.getId(), employee);
    }

    // 查询所有员工
    public Collection<Employee> getAll() {
        return employees.values();
    }

    public Employee get(Integer id) {
        return employees.get(id);
    }

    public void delete(Integer id) {
        employees.remove(id);
    }
}
