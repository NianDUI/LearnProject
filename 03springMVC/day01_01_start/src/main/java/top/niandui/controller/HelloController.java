package top.niandui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 控制器类
 *
 * @RequestMapping注解：
 *  属性：
 *      value：和path一样，表示路径
 *      method：指定请求的方式
 *      params：指定限制请求参数的条件
 *          params = {"username"}  表示请求参数必须有username
 *          params = {"username=heihei"} 表示请求参数必须有username，并且等于heihei
 *      headers：发送的请求中必须包含的请求头
 *
 *
 */
@Controller
@RequestMapping(path = "/user")
public class HelloController {

    @RequestMapping(path = "/hello")
    public String sayHello() {
        System.out.println("Hello SpringMVC");
        return "success";
    }


    /**
     * RequestMapping注解
     * @return
     */
    @RequestMapping(value = "/testRequestMapping", method = {RequestMethod.GET, RequestMethod.POST},
        params = {"username=hehe"}, headers = {"Accept"})
    public String testRequestMapping() {
        System.out.println("测试RequestMapping注解");
        return "success";
    }

}
