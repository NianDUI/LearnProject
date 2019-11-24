package top.niandui.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 *      必须实现 HandlerInterceptor 接口
 */
public class MyInterceptor2 implements HandlerInterceptor {

    /**
     * 预处理，controller方法执行前
     * return true  放行，执行下一个拦截器，如果没有，执行controller中的方法
     * return false 不放行
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器 MyInterceptor2 的 preHandle() 方法执行了");

        // 跳转页面
//        request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);

//        response.sendRedirect("");

        return true;
    }

    /**
     * 后处理方法，controller方法执行之后，success.jsp执行之前
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("拦截器 MyInterceptor2 的 postHandle() 方法执行了");

        // 跳转页面
//        request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
    }

    /**
     * success.jsp页面执行之后，该方法执行
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("拦截器 MyInterceptor2 的 afterCompletion() 方法执行了");
    }
}
