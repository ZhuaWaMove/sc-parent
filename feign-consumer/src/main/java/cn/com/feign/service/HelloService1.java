package cn.com.feign.service;

import cn.com.feign.domain.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by new on 2017/12/24.
 * ,fallback = HelloServiceFallBack.class
 */
@FeignClient(value = "hello-service" )
public interface HelloService1 {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello();

    @RequestMapping(value = "/hello2",method = RequestMethod.GET)
    public String hello2(@RequestParam("name") String name);

    @RequestMapping(value="/hello3",method = RequestMethod.GET)
    public String hello3(@RequestHeader("name") String name, @RequestHeader("age") String age);

    @RequestMapping(value="/hello4",method = RequestMethod.POST)
    public String hello4( @RequestBody User user);

}
