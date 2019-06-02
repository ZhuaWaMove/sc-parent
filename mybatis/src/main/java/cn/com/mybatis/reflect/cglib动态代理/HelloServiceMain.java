package cn.com.mybatis.reflect.cglib动态代理;

import cn.com.mybatis.reflect.jdk动态代理.HelloService;
import cn.com.mybatis.reflect.jdk动态代理.HelloServiceImpl;
import cn.com.mybatis.reflect.jdk动态代理.HelloServiceProxy;

/**
 * Created by GL-shala on 2018/7/14.
 */
public class HelloServiceMain {
    public static void main(String[] args) {
       HelloServiceCglib helloServiceCglib = new HelloServiceCglib();
        HelloService helloService = (HelloService) helloServiceCglib.getInstance(new HelloServiceImpl());
        helloService.sayHello("菜逼");
    }
}
