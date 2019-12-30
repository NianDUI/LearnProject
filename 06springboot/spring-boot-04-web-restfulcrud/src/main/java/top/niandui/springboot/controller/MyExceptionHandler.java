package top.niandui.springboot.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.niandui.springboot.exception.UserNotExistException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 四、Web开发 7、错误处理机制、2如何定制错误响应
 * 自定义异常处理器
 *      ：指定异常的处理
 *      使用@ControllerAdvice 注解 该类
 *      使用@ExceptionHandler(异常类.class) 注解 处理的方法
 *
 * 示例：http://localhost:8080/crud/hello?user=aaa
 *
 */
@ControllerAdvice
public class MyExceptionHandler {
    // 自定义异常处理

    // 第一种写法
    // 浏览器和客户端返回的都是json数据
//    @ResponseBody // 返回的是json数据
//    @ExceptionHandler(UserNotExistException.class)
//    public Map<String, Object> handleException(Exception e) {
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("code", "user.notexist");
//        map.put("message", e.getMessage());
//
//        return map;
//    }



    // 第二种方式
    // 转发到/error进行自适应响应效果处理
    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e, HttpServletRequest request) {
        // 传入我们自己的作物状态码 4xx 5xx , 否则就不会进入定制错误页面的解析流程
        /**
         * BasicErrorController.errorHtml/error(BasicErrorController.getErrorAttributes.this.errorAttributes.getErrorAttributes()方法)
         * DefaultErrorAttributes.getErrorAttributes(this.addStatus()方法)
         * Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
         */
        request.setAttribute("javax.servlet.error.status_code", 500);

        Map<String, Object> map = new HashMap<>();
        map.put("code", "user.notexist");
        map.put("message", "用户出错了");
        request.setAttribute("ext", map);

        // 转发到 /error 由 BasicErrorController 处理
        return "forward:/error";
    }

    // 第三种方式
    // 将我们定制的数据携带出去
    // 自定义 ErrorAttributes 组件
}
