package top.niandui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import top.niandui.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 响应之返回值（几种请求转发或重定向）
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 1 返回类型：String
     * @param model
     * @return
     */
    @RequestMapping("/testString")
    public String testString(Model model) {
        System.out.println("执行了... testString()方法");
        // 模拟从数据库中查询出User对象
        User user = new User();
        user.setUsername("美美");
        user.setPassword("123");
        user.setAge(30);
        // modeld对象
        model.addAttribute("user", user);
        return "success";
    }

    /**
     * 2 返回类型：void
     *      默认情况：
     *          路径 + 视图解析器
     *              Message /day02_01_response_war/WEB-INF/pages/user/testVoid.jsp
     * 手动调用转发，不会使用视图解析器
     *
     * 请求转发，一次请求，不用编写项目名称
     *      路径：
     *          无/：当前路径     success     /day02_01_response_war/user/success
     *          有/：项目根路径   /success    /day02_01_response_war/success
     * 响应重定向，两次请求，需要写项目名称
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/testVoid")
    public void testVoid(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("执行了... testVoid()方法");
        // 编写请求转发程序
//        request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request, response);

        // 响应重定向
//        response.sendRedirect(request.getContextPath() + "/index.jsp");

        // 设置中文乱码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // 直接进行响应
        response.getWriter().print("你好");

    }

    /**
     * 3 返回类型：ModelAndView
     * @return
     */
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView() {
        System.out.println("执行了... testModelAndView()方法");
        // 创建ModelAndView对象
        ModelAndView mv = new ModelAndView();
        // 模拟从数据库中查询出User对象
        User user = new User();
        user.setUsername("小风");
        user.setPassword("456");
        user.setAge(30);

        // 把user对象存储到mv对象中，也会把user对象存入到request域当中（底层使用的是ModelMap）
        mv.addObject("user", user);

        // 想跳转到哪个页面--会使用视图解析器
        mv.setViewName("success");

        return mv;
    }


    /**
     * 使用关键字的方式进行转发或重定向
     * @return
     */
    @RequestMapping("/testForwardOrRedirect")
    public String testForwardOrRedirect() {
        System.out.println("执行了... testForwardOrRedirect()方法");

        // 不需要项目名 框架默认给加了

        // 请求的转发
        //      固定语法："forward:路径...文件"  不需要项目名
//        return "forward:/WEB-INF/pages/success.jsp";

        // 重定向：
        //      固定语法："redirect:路径...文件" 不需要项目名

        return "redirect:/index.jsp";
    }


    /**
     * 模拟异步请求和响应
     */
    @RequestMapping("/testAjax")
    public @ResponseBody User testAjax(@RequestBody User user) {
        System.out.println("执行了... testAjax()方法");
        // 客户端发送ajax的请求，传的是json字符串，后端把json字符串封装到user对象中
        System.out.println(user);

        // 做相应
        user.setUsername("haha");
        user.setAge(40);

        // 做相应
        return user;
    }

}