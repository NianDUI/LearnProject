package top.niandui.starter;

/**
 * HelloService
 */
public class HelloService {

    HelloProperties helloProperties;

    public String sayHelloNinadui(String name) {
        return helloProperties.getPrefix() + "-" +  name + "-" + helloProperties.getSuffix();
    }
}
