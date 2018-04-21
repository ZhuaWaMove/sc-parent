package cn.com.ribbom.controller;

import cn.com.ribbom.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by new on 2017/12/24.
 */
@RestController
public class RibbonController {

    @Autowired
    HelloService helloService;
    @RequestMapping(value="/ribbon-consumer",method = RequestMethod.GET)
    public String helloService(){
        return helloService.helloSerivce();
    }
}
