package top.niandui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {


    @RequestMapping("/testInterceptor")
    public String testInterceptor() throws Exception {
        System.out.println("方法执行了..... testInterceptor()");

        return "success";
    }
}
