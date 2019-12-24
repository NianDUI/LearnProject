package top.niandui.springboot.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.niandui.springboot.exception.UserNotExistException;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(@RequestParam("user") String user) {
        if ("aaa".equals(user)) {
            throw new UserNotExistException();
        }
        return "Hello World";
    }

    // 查出一些数据，在页面展示
    @RequestMapping("/success")
    public String success(Map<String, Object> map) {
        map.put("hello", "<h1>你好</h1>");
        map.put("users", Arrays.asList("zhangsan", "lisi", "wangwu"));
        // classpath:/templates/success.html
        return "success";
    }

    @ResponseBody
    @RequestMapping("/testData")
    public Map testData(Date data, Timestamp timestamp) {
        System.out.println(data);
        System.out.println(timestamp);
        Map map = new HashMap();
        map.put("data", data);
        map.put("timestamp", timestamp);
        return map;
    }

}
