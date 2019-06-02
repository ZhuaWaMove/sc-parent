package cn.com.mybatis.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by GL-shala on 2018/7/14.
 */
public class ReflectService {

    public void sayHello(String name){
        System.out.println("hello "+name);
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //通过反射创建对象
        Object o = Class.forName(ReflectService.class.getName()).newInstance();

        //获取服务方法 sayhello
        Method sayHello = o.getClass().getMethod("sayHello", String.class);

        sayHello.invoke(o,"小明");
    }
}
