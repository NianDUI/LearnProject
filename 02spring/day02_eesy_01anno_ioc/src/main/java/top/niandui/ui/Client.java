package top.niandui.ui;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import top.niandui.dao.IAccountDao;
import top.niandui.service.IAccountService;
import top.niandui.service.Impl.AccountServiceImpl;

/**
 * 模拟一个表现层，用于调用业务层
 */
public class Client {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // 1. 获取核心容器对象
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");

        // 2. 根据id胡Bean对象
        IAccountService as = (IAccountService) ac.getBean("accountService");

//        System.out.println(as);
//
//        IAccountDao adao = ac.getBean("accountDao", IAccountDao.class);
//        System.out.println(adao);
//        IAccountService as2 = (IAccountService) ac.getBean("accountService");
//        System.out.println(as == as2);

        as.saveAccount();
        ac.close();
    }
}
