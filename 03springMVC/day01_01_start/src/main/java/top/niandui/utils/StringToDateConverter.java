package top.niandui.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 把字符串转换成日期
 */
public class StringToDateConverter implements Converter<String, Date> {

    /**
     * @param source    传入进来的字符串
     * @return
     */
    @Override
    public Date convert(String source) {
        // 判空
        if (source == null) {
            throw new RuntimeException("请传入数据");
        }
        try {
            // 把字符串转换成日期
            return new SimpleDateFormat("yyyy-MM-dd").parse(source);
        } catch (Exception e) {
            throw new RuntimeException("数据格式应为：yyyy-MM-dd");
        }
    }
}
