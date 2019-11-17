package top.niandui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import top.niandui.domain.User;

import java.sql.SQLOutput;
import java.util.Date;
import java.util.Map;

/**
 * 常用的注解
 */
@Controller
@RequestMapping("anno")
@SessionAttributes(value = {"msg"}) // 把msg=美美，也存入到session域中一份
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

    /**
     * 获取请求头的值
     * @RequestHeader 注解：
     *          用于获取请求消息头，  一般不用
     *      value 或 name：提供消息头名称
     *      required：是否必须有此请求头，默认为true
     * @param header
     * @return
     */
    @RequestMapping("testRequestHeader")
    public String testRequestHeader(@RequestHeader("Host") String header) {
        System.out.println("执行了 testRequestHeader()");
        System.out.println(header);

        return "success";
    }

    /**
     * 获取cookie的值
     * @CookieValue 注解：
     *          用于把指定cookie名称的值传入控制器的方法参数
     *      value 或 name：指定cookie的名称
     *      required：是否必须有此cookie，默认为true
     * @param cookieValue
     * @return
     * JSESSIONID：创建session后默认会传的
     */
    @RequestMapping("testCookieValue")
    public String testCookieValue(@CookieValue(value = "JSESSIONID") String cookieValue) {
        System.out.println("执行了 testCookieValue()");
        System.out.println(cookieValue);

        return "success";
    }


    // start @ModelAttribute
    /**
     * @ModelAttribute 注解：
     *          在方法上：表示当前方法会在控制器的方法执行之前，先执行。它可以修饰没有返回值的方法，也可以修饰有具体返回值的方法
     *          在参数上：获取指定的数据给参数赋值
     *      value 或 name：用于获取数据的key。key可以是POJO的属性名称，也可以是map结构的key。
     *      binding：，默认为true
     *      testModelAttribute
     */
    @RequestMapping("testModelAttribute")
//    public String testModelAttribute(User user) { // 方法有返回值
    public String testModelAttribute(@ModelAttribute("abc") User user) { // 方法无返回值
        System.out.println("执行了 testModelAttribute()");
        System.out.println(user);
        return "success";
    }

    // 加在方法上

    /**
     * @ModelAttribute 加在方法上
     * 该方法先执行
     *      1方法有返回值：
     *          会用该返回值中的信息，给请求参数中值给为null的赋值。
     *      2方法没有返回值：
     *          需要在该方法的参数中提供一个map集合，将需要的参数存到map集合当中
     *          使用时只需要的之后的方法，在参数上使用@ModelAttribute("key")，即可获取到数据
     *
     *      之后的控制器，会用请求参数覆盖掉，ModelAtrribute注解方法传回的参数
     *      User{uname='阿萨德', age=18, date=Sun Nov 17 15:38:28 GMT+08:00 2019}
     *
    @ModelAttribute
    public User showUser(String uname) {
        System.out.println("执行了 showUser()");
        // 通过用户查询数据库（模拟）
        User user = new User();
        user.setUname(uname);
        user.setAge(20);
        user.setDate(new Date());
        return user;
    }
     */
    @ModelAttribute
    public void showUser(String uname, Map<String, User> Map) {
        System.out.println("执行了 showUser()");
        // 通过用户查询数据库（模拟）
        User user = new User();
        user.setUname(uname);
        user.setAge(20);
        user.setDate(new Date());
        Map.put("abc", user);
    }
    // end @ModelAttribute


    // start @SessionAttributes
    /**
     * @SessionAttributes 注解：
     *          用于多次执行控制器方法间的参数共享
     *      value 或 names：用于指定存入的属性名称   String[]
     *      type：用于指定存入的数据类型
     *      注：
     *          只能作用的类上
     */
    @RequestMapping("testSessionAttribute")
    public String testSessionAttribute(Model model) {
        System.out.println("执行了 testSessionAttribute()");
        // 底层会存储到request域中
        model.addAttribute("msg", "美美");
        return "success";
    }

    /**
     * 从session域中取值
     * @param modelMap
     * @return
     */
    @RequestMapping("getSessionAttribute")
    public String getSessionAttribute(ModelMap modelMap) {
        System.out.println("执行了 getSessionAttribute()");
        System.out.println(modelMap.get("msg"));
        return "success";
    }

    /**
     * 清空session中的信息
     * @param staus
     * @return
     */
    @RequestMapping("delSessionAttribute")
    public String delSessionAttribute(SessionStatus staus) {
        System.out.println("执行了 delSessionAttribute()");
        // 清空session中的信息
        staus.setComplete();
        return "success";
    }
    // end @SessionAttributes
}
