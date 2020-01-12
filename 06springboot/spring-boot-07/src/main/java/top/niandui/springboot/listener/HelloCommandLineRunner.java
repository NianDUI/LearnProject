package top.niandui.springboot.listener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 命令行运行器
 *      只需要放在ioc容器中
 * HelloCommandLineRunner
 *      实现 CommandLineRunner 接口
 */
@Component
public class HelloCommandLineRunner implements CommandLineRunner {

    /**
     * ioc容器完全加载完成后被调用
     *      在 ApplicationRunner 之后被调用
     * SpringApplication.run().callRunners().run(args);
     * @param args  命令行参数
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner...run..." + Arrays.asList(args));
    }
}
