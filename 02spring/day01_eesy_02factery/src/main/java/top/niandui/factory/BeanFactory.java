package top.niandui.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 一个创建 Bean 对象的工程
 *
 * Bean ： 在计算机英语中，有可重用组建的含义
 * JavaBean ：用Java语言编写的可重用组件。
 *      JavaBean > 实体类
 *
 *      他就是创建我们的 service 和 dao 对象
 *
 *      第一个：需要一个配置文件来配置我们的 service 和 dao
 *              配置的内容：唯一标识 = 全限定类名（key = value）
 *      第二个：通过读取配置文件中配置的内容，反射创建对象
 *
 *      我的配置文件可以是 xml 也可是 properties
 */
public class BeanFactory {

    // 定义一个 properties 对象
    private static Properties props;

    // 定义一个 Map，用于存放我们要创建的对象。我们把它称之为容器
    private static Map<String, Object> beans;

    // 使用静态代码块为 properties 对象赋值
    static {
        try {
            // 实例化对象
            props = new Properties();
            // 获取 properties 文件的流对象
            InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            props.load(in);
            // 实例化容器
            beans = new HashMap<String, Object>();
            // 取出配置文件中的所有key
            Enumeration<Object> keys = props.keys();
            // 遍历枚举
            while (keys.hasMoreElements()) {
                // 取出每个key
                String key = keys.nextElement().toString();
                // 根据key 获取 value
                String beanPath = props.getProperty(key);
                // 反射创建对象
                Object bean = Class.forName(beanPath).newInstance();
                // 把key 和 value 存入存入容器中
                beans.put(key, bean);
            }
        } catch (Exception e) {
            throw new ExceptionInInitializerError();
        }
    }
    /**
     * 根据 Bean 的名称获取 bean 对象
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        return beans.get(beanName);
    }


    /**
     * 根据 Bean 的名称获取 bean 对象
     * @param beanName
     * @return
     */
    /*public static Object getBean(String beanName) {
        Object bean = null;
        try {
            String beanPath = props.getProperty(beanName);
            bean = Class.forName(beanPath).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }*/


}
