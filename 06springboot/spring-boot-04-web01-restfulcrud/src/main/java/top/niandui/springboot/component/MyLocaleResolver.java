package top.niandui.springboot.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 国际化：
 * 自己的区域信息解析器，实现 LocaleResolver 接口
 *
 * 可以在链接上携带区域信息
 *
 */
public class MyLocaleResolver implements LocaleResolver {

    // 解析区域信息
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String l = request.getParameter("l");
        Locale locale = Locale.getDefault(); // 获取系统默认的区域信息
        if (!StringUtils.isEmpty(l)) {
            String[] split = l.split("_");
            locale = new Locale(split[0], split[1]); // 语言代码和国家代码
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
