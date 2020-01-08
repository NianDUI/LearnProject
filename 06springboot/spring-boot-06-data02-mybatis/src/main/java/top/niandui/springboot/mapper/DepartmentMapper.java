package top.niandui.springboot.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Repository;
import top.niandui.springboot.bean.Department;

/**
 * @Title: DepartmentMapper.java
 * @description: DepartmentMapper
 * @time: 2020/1/8 21:42
 * @author: liyongda
 * @version: 1.0
 */
// 指定这是一个操作数据库的mapper
//@Mapper
@Repository
public interface DepartmentMapper {

    @Select("select * from department where id=#{id}")
    Department getDeptById(Integer id);

    @Delete("delete from department where id=#{id}")
    Integer deleteDeptById(Integer id);

    // 使用自动生成的主键, 指定主键属性的名称
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into department(department_name) values(#{departmentName})")
    Integer insertDept(Department department);

    @Update("update department set department_name=#{departmentName} where id=#{id}")
    Integer updateDept(Department department);

}
