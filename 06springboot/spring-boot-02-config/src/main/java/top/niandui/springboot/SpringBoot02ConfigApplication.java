package top.niandui.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 *
 *
 * 配置文件
 *      Spring Boot使用一个全局的配置文件，配置文件名是固定的；
 *          application.properties
 *          application.yml
 *      作用：
 *          修改SpringBoot自动配置的默认值；
 *      SpringBoot在底层都给我们自动配置好了；
 *
 *      YAML（YAML Ain't Markup Language）
 *          YAML A Markup Language：是一个标记语言
 *          YAML isn't Markup Language：不是一个标记语言
 *          YAML: 以数据为中心，比json和xml更适合做配置文件    www.yaml.org
 *                  相同的配置
 *
 *      标记语言：
 *          以前的配置文件；大多都使用的是 xxxx.xml文件；
 *
 *      配置示例：
 *      YAML:
 *          server:
 *            port: 8081
 *      XML:
 *          <server>
 *              <port>8081</port>
 *          </server>
 *
 * 二、YAML 语法：
 *      1、YAML 基本语法
 *          - k:(空格)v : 表示一对键值对(空格必须有)，示例port: 8081
 *          - 使用缩进表示层级关系
 *          - 缩进时不允许使用Tab键，只允许使用空格
 *          - 缩进的空格数目不重要，只要相同层级的元素左对齐即可
 *          - 大小写敏感(属性和值)
 *
 *      2、YAML 支持的三种数据结构
 *          - 对象：键值对的集合(属性和值：Map)
 *              对象还是k: v的方式，在下一行来写属性和值的关系；注意缩进
 *              friends:
 *                  lastName: zhangsan
 *                  age: 20
 *              行内写法：
 *                  friends: {lastName: zhangsan,age: 18}
 *
 *          - 数组：一组按次序排列的值(List、Set)
 *              用- 值表示数组中的一个元素
 *              pets:
 *                  - cat
 *                  - dog
 *                  - pig
 *              行内写法：
 *                  pets: [cat,dog,pig]
 *
 *          - 字面量：单个的、不可再分的值(数字，字符串，布尔值)
 *              k: v ：字面直接来写；
 *                  字符串默认不用加上单引号或者双引号；
 *                  ""：双引号：不会转义字符串里面的特殊字符；特殊字符会作为本身想表示的意思
 *                          (输出时保留不会原样，保留原有格式)
 *                      name："zhangsan \n lisi"：输出；zhangsan 换行 lisi
 *                      (保存:"zhangsan \n lisi",输出:"zhangsan (换行) lisi") 输出时这里的\会转义后面的
 *                  ''：单引号：会转义特殊字符，特殊字符最终只是一个普通的字符输出
 *                          (输出时保留原样)
 *                      name：'zhangsan \n lisi'：输出；zhangsan \n lisi
 *                      (保存:"zhangsan \\n lisi",输出:"zhangsan \n lisi") 输出时这里的\就是一个普通的\，因为本身存储时就是\\
 *
 *                  !! 转类型
 *                      !! str 123
 *
 * 三、配置文件值注入：
 *      先在pom文件中导入一个配置文件处理器，以后编写就会有提示了
 *      配置文件：
 *          person:
 *              # lastName: zhangsan
 *              last-name: zhangsan
 *              age: 18
 *              boss: false
 *              birth: 2017/12/12
 *              maps: {k1: v1,k2: 12}
 *              lists:
 *                  - lisi
 *                  - zhaoliu
 *              dog:
 *                  name: 小狗
 *                  age: 2
 *      JavaBean：
 *          @Component
 *          @ConfigurationProperties(prefix = "person")
 *          public class Person {
 *              private String lastName;
 *              private Integer age;
 *              private Boolean boss;
 *              private Date birth;
 *              private Map<String, Object> maps;
 *              private List<Object> lists;
 *              private Dog dog;
 *          }
 *
 */
@SpringBootApplication
//@ImportResource(locations = {"classpath:bean.xml"})  // 加载指定的Spring配置文件
public class SpringBoot02ConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot02ConfigApplication.class, args);
//        System.out.println("zhangsan \n lisi");
    }

}
