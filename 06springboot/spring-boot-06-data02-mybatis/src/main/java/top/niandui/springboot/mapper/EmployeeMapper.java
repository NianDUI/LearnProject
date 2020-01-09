package top.niandui.springboot.mapper;

import org.springframework.stereotype.Repository;
import top.niandui.springboot.bean.Employee;

/**
 * @Title: EmployeeMapper.java
 * @description: TODO
 * @time: 2020/1/9 20:50
 * @author: liyongda
 * @version: 1.0
 */
// 使用@Mapper或则@MapperScan接口扫描装配到容器中
@Repository
public interface EmployeeMapper {

    Employee getEmpById(Integer id);

    Integer insertEmp(Employee employee);
}
