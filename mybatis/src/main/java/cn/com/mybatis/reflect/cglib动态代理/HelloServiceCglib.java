package cn.com.mybatis.reflect.cglib动态代理;

import cn.com.mybatis.reflect.jdk动态代理.HelloService;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by GL-shala on 2018/7/14.
 * cglib 动态代理
 */
public class HelloServiceCglib implements MethodInterceptor{


    //被代理对象
    private Object target;

    /**
     * 创建代理对象
     * @param target
     * @return
     */
    public Object getInstance(Object target){
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        //回调方法
        enhancer.setCallback(this);
        //创建代理对象
        return enhancer.create();
    }
    /**
     *回调方法
     * @param o
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("######我是cglib动态代理######");
        //反射方法前调用
        System.out.println("我准备说hello");

        Object o1 = methodProxy.invokeSuper(o, objects);

        //反射方法后调用
        System.out.println("我说过hello了");

        return o1;
    }
}
