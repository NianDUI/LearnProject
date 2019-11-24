package top.niandui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import top.niandui.exception.SysException;

/**
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {


    @RequestMapping("/testException")
    public String testException() throws SysException {
        System.out.println("方法执行了..... testException()");

        try {
            // 模拟异常
            int a = 10 / 0;
        } catch (Exception e) {
            // 打印异常信息
            e.printStackTrace();

            // 抛出自定义异常信息
            throw new SysException("查询所有用户出现错误了。。。");
        }

        return "success";
    }
}
