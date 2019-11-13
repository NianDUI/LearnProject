package top.niandui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
 *          a
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

}
