package cn.com.mybatis.reflect.jdk动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by GL-shala on 2018/7/14.
 *
 * 服务代理类
 *
 * 提供真实对象的绑定和代理方法，代理类的要求是实现InvocationHandler接口的代理方法，
 * 当一个对象被绑定后，执行其方法的时候会进入到代理方法里
 */
public class HelloServiceProxy implements InvocationHandler {

    //真实服务对象
    private Object target;

    /**
     * 绑定委托对象 并返回一个代理类
     * @param target
     * @return
     */
    public Object bind(Object target){
        System.out.println("绑定被代理对象ing...");
        this.target = target;
        //取得代理对象
        return Proxy.newProxyInstance(target.getClass().getClassLoader() ,target.getClass().getInterfaces(), this);
    }

    /**
     * 通过代理对象调用方法首先进入这个方法
     * @param proxy 代理对象
     * @param method  被调用方法
     * @param args 方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("#######我是JDK动态代理#######");
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        }
        Object result = null;
        //反射方法前调用
        System.out.println("我准备说hello。");
        //执行方法 相当于调用HelloServiceImpl类的sayHello方法
        result = method.invoke(target , args);
        //反射方法后调用
        System.out.println("我说过hello了");
        return result;
    }
}
