package cn.com.mybatis.reflect.jdk动态代理;

/**
 * Created by GL-shala on 2018/7/14.
 *
 * 服务类
 */
public class HelloServiceImpl implements HelloService{
    @Override
    public void sayHello(String name) {
        System.out.println("Hello,"+name);
    }
}
