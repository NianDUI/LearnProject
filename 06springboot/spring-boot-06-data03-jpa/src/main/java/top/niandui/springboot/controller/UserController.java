package top.niandui.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.niandui.springboot.entity.User;
import top.niandui.springboot.repository.UserRepository;

/**
 * @Title: UserController.java
 * @description: UserController
 * @time: 2020/1/12 16:08
 * @author: liyongda
 * @version: 1.0
 */
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Integer id) {
        User user = userRepository.findById(id).get();
        return user;
    }

    @GetMapping("/user")
    public User insertUser(User user) {
        User save = userRepository.save(user);
        return save;
    }
}
