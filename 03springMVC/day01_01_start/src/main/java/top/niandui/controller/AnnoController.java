package top.niandui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLOutput;

/**
 * 常用的注解
 */
@Controller
@RequestMapping("anno")
public class AnnoController {

    /**
     * @RequestParam 注解：
     *          绑定指定的参数
     *      value 或 name：指定参数的key
     *      required：该参数是否必须的，默认为true
     * @param username
     * @return
     */
    @RequestMapping("testRequestParam")
    public String testRequestParam(@RequestParam(name = "name") String username, @RequestParam(value = "psw", required = false) String password) {
        System.out.println("执行了 testRequestParam()");
        System.out.println(username);
        System.out.println(password);
        return "success";
    }

    /**
     * 获取请求体的内容
     * @RequestBody 注解：
     *          用于获取请求体的内容。直接得到的是键值对结构的数据。get请求不适用
     *      required：请求体是否必须的，默认为true
     * @param body
     * @return
     */
    @RequestMapping("testRequestBody")
    public String testRequestBody(@RequestBody String body) {
        System.out.println("执行了 testRequestBody()");
        System.out.println(body);
        return "success";
    }

    /**
     * @PathVariable 注解：
     *          用于绑定URl中的占位符。
     *      value 或 name：用于指定URL中的占位符名称
     *      required：是否必须提供占位符，默认为true
     *
     * @param id
     * @return
     */
    @RequestMapping("testPathVariable/{sid}")
    public String testPathVariable(@PathVariable("sid") String id) {
        System.out.println("执行了 testPathVariable()");
        System.out.println(id);
        return "success";
    }
}
