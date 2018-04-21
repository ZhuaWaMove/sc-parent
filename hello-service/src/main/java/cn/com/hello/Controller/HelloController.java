package cn.com.hello.Controller;

import cn.com.hello.domain.User;
import com.netflix.discovery.DiscoveryManager;
import com.netflix.discovery.EurekaClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.dc;

/**
 * Created by new on 2017/12/24.
 */
@RestController
public class HelloController extends HttpServlet{

    private final Logger logger = LoggerFactory.getLogger(HelloController.class);


    @RequestMapping(value="/hello",method = RequestMethod.GET)
    public String hello(HttpServletRequest hsr){
        String requestURI = null;
        String pathInfo = null;
        String localPort = null ;

        requestURI = hsr.getRequestURI();
        pathInfo = hsr.getRemoteAddr();
        localPort =String.valueOf(hsr.getRemotePort()) ;

        Thread th=Thread.currentThread();
        long thId = th.getId();
        logger.info("当前线程号："+thId);

        return "hello-service,uri:"+requestURI+pathInfo+":"+localPort;
    }
    @RequestMapping(value="/hello2",method = RequestMethod.GET)
    public String hello2(HttpServletRequest hsr, @RequestParam("name")String name){
        String requestURI = null;
        String pathInfo = null;
        String localPort = null ;

        requestURI = hsr.getRequestURI();
        pathInfo = hsr.getRemoteAddr();
        localPort =String.valueOf(hsr.getRemotePort()) ;
        return "hello-service,uri:"+requestURI+pathInfo+":"+localPort+" 请求参数：name="+name;
    }
    @RequestMapping(value="/hello3",method = RequestMethod.GET)
    public String hello3(HttpServletRequest hsr, @RequestHeader String name,@RequestHeader String age){
        String requestURI = null;
        String pathInfo = null;
        String localPort = null ;

        requestURI = hsr.getRequestURI();
        pathInfo = hsr.getRemoteAddr();
        localPort =String.valueOf(hsr.getRemotePort()) ;
        return "hello-service,uri:"+requestURI+pathInfo+":"+localPort+" 请求参数：user="+new User(name,age);
    }
    @RequestMapping(value="/hello4",method = RequestMethod.POST)
    public String hello4(HttpServletRequest hsr, @RequestBody User user){
        String requestURI = null;
        String pathInfo = null;
        String localPort = null ;

        requestURI = hsr.getRequestURI();
        pathInfo = hsr.getRemoteAddr();
        localPort =String.valueOf(hsr.getRemotePort()) ;
        return "hello-service,uri:"+requestURI+pathInfo+":"+localPort+" 请求参数：name="+user.getName()+" age="+user.getAge();
    }

    @RequestMapping(value = "/getInstance",method = RequestMethod.GET)
    public String getInstances(){

        List<String> list = discoveryClient().getServices();
       System.out.println("Services:"+list);
        List<ServiceInstance> list1 = discoveryClient().getInstances("eureka-server1");
        System.out.println("Instances:"+list1);

        ServiceInstance localServiceInstance = discoveryClient().getLocalServiceInstance();
        System.out.println("localServiceInstance:"+localServiceInstance);
        EurekaClientConfig eurekaClientConfig = DiscoveryManager.getInstance().getEurekaClient().getEurekaClientConfig();
        return null;
    }

    public DiscoveryClient discoveryClient(){
       return new DiscoveryClient() {
           @Override
           public String description() {
               return null;
           }

           @Override
           public ServiceInstance getLocalServiceInstance() {
               return null;
           }

           @Override
           public List<ServiceInstance> getInstances(String s) {
               return null;
           }

           @Override
           public List<String> getServices() {
               return null;
           }
       };
    }
}
