package top.niandui.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.niandui.springboot.entity.User;

/**
 * @Title: UserRepository.java
 * @description: 操作实体类对应数据表的接口
 * @time: 2020/1/9 21:48
 * @author: liyongda
 * @version: 1.0
 *
 * 继承 JpaRepository<T, ID> T: 要操作那个实体类，ID: 实体类主键的类型
 */
// 继承JpaRepository来完成对数据库的操作
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
