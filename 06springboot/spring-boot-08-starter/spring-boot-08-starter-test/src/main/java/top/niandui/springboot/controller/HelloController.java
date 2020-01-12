package top.niandui.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.niandui.starter.HelloService;

/**
 * @Title: HelloController.java
 * @description: HelloController
 * @time: 2020/1/12 20:10
 * @author: liyongda
 * @version: 1.0
 */
@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @GetMapping("/hello")
    public String sayHello() {
        return helloService.sayHelloNinadui("haha");
    }
}
