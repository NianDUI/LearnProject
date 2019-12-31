package top.niandui.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 登录Controller
 */
@Controller
public class LoginController {

    //    @DeleteMapping
//    @PutMapping
//    @GetMapping
//    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password
            , Map<String, Object> map, HttpSession session) {

        if (!StringUtils.isEmpty(username) && "123456".equals(password)) {
            // 登录成功，防止表单提交，可以重定向到主页
            session.setAttribute("loginUser", username);
            return "redirect:/main.html";
        } else {
            map.put("message", "用户名密码错误");
            // 登录失败
            return "login";
        }
    }
}
