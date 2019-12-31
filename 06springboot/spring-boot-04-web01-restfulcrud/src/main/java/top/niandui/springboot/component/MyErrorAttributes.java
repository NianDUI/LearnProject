package top.niandui.springboot.component;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 四、Web开发 7、错误处理机制、2如何定制错误响应
 * 自定义错误属性
 * 继承 DefaultErrorAttributes 类 或者 实现 ErrorAttributes 接口
 *
 * @Component 给容器中加入我们自己定义的 ErrorAttributes
 *
 * 示例：http://localhost:8080/crud/hello?user=aaa
 */
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {

    // 返回值的map就是页面和json能获取的所有字段
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        map.put("company", "niandui");

        // RequestAttributes.SCOPE_REQUEST = 0 request域中
        // 我们的异常处理器携带的数据
        Map<String, Object> ext = (Map<String, Object>) webRequest.getAttribute("ext", RequestAttributes.SCOPE_REQUEST);
        map.put("ext", ext);
        return map;
    }
}
