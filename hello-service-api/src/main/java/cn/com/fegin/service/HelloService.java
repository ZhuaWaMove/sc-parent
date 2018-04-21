package cn.com.fegin.service;


import cn.com.fegin.user.User;
import org.springframework.web.bind.annotation.*;

/**
 * Created by new on 2017/12/24.
 *
 */
@RequestMapping(value = "/refactor")
public interface HelloService {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello();

    @RequestMapping(value = "/hello5",method = RequestMethod.GET)
    public String hello(@RequestParam("name") String name);

    @RequestMapping(value="/hello6",method = RequestMethod.GET)
    public String hello(@RequestHeader("name") String name, @RequestHeader("age") String age);

    @RequestMapping(value="/hello7",method = RequestMethod.POST)
    public String hello(@RequestBody User user);

}
