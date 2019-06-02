package cn.com.mybatis.reflect.jdk动态代理;

/**
 * Created by GL-shala on 2018/7/14.
 */
public class HelloServiceMain {
    public static void main(String[] args) {
        HelloServiceProxy helloServiceProxy = new HelloServiceProxy();
        System.out.println("bind start...");
        HelloService o = (HelloService) helloServiceProxy.bind(new HelloServiceImpl());
        System.out.println("bind end...");
        o.sayHello("笑话");
    }
}
