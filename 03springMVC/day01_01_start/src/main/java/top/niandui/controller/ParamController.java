package top.niandui.controller;

import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import top.niandui.domain.Account;
import top.niandui.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  请求参数的绑定：
 *      绑定说明：
 *          绑定机制：键值对，反射
 *          支持的数据类型
 *              1、基本数据类型和字符串类型
 *              2、实体类型（JavaBean）
 *              3、集合类型（List，Map集合等）
 *
 *      基本类型和字符串类型
 *          MVC会自动的将方法的参数，与请求的参数绑定
 *              要求：参数名称和key值相同，类型可转化
 *      JavaBean类型：（实体类型）
 *          基本数据类型和字符串：参数的key和Bean属性想对应。
 *          引用类型：Bean中的Bean属性赋值，传参时候的key以“Bean属性名.属性名”
 *              user.uname
 *              list[0].uname
 *              map['one'].uname
 *
 *      中文乱码：
 *          通过过滤器，在web.xml
 *          CharacterEncodingFilter过滤器
 *
 *      默认会进行类型转换
 *              public interface Converter<S, T>接口：类型转化接口，S:String，T：目标类型
 *          大多数：String -> *
 *          其他：自定义类型转化器
 *
 *          2000/11/11 -> Date 正常：date=Sat Nov 11 00:00:00 GMT+08:00 2000
 *
 *      获取到原生的Servlet的API
 *          直接在方法上加，相应的类型参数
 *
 */
@Controller
@RequestMapping("/param")
public class ParamController {

    /**
     * 请求参数绑定
     *
     * @return
     */
    @RequestMapping("/testParam")
    public String testParam(String username, String password) {
        System.out.println("执行了testParam()");
        System.out.println("username:" + username);
        System.out.println("password:" + password);
        return "success";
    }


    /**
     * 请求参数的绑定, JavaBean类型
     * @param account
     * @return
     */
    @RequestMapping("/saveAccount")
    public String saveAccount(Account account) {
        System.out.println("执行了 saveAccount()");
        System.out.println(account);
        return "success";
    }


    /**
     * 自定义类型转化器
     * @param user
     * @return
     */
    @RequestMapping("/saveUser")
    public String saveUser(User user) {
        System.out.println("执行了 saveUser()");
        System.out.println(user);
        return "success";
    }

    /**
     * 获取到原生的Servlet的API
     * @return
     */
    @RequestMapping("/testServlet")
    public String testServlet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行了 testServlet()");
        System.out.println(request);
        System.out.println(request.getSession());
        System.out.println(request.getSession().getServletContext());
        System.out.println(response);
        return "success";
    }
}
