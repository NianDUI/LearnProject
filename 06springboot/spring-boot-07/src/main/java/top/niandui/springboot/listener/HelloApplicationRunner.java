package top.niandui.springboot.listener;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 应用运行器
 *      只需要放在ioc容器中
 * HelloApplicationRunner
 *      实现 ApplicationRunner 接口
 */
@Component
public class HelloApplicationRunner implements ApplicationRunner {

    /**
     * ioc容器完全加载完成后被调用
     *      在 CommandLineRunner 之前被调用
     * SpringApplication.run().callRunners().run(args);
     * @param args  命令行参数包装后的对象
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("ApplicationRunner...run...");
    }
}
