package cn.com.feign.controller;
import cn.com.fegin.service.HelloService;
import cn.com.feign.domain.User;
import cn.com.feign.service.HelloService1;
import cn.com.feign.service.RefactorHelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by new on 2017/12/24.
 */
@RestController
public class FeignController {

    private final Logger logger = LoggerFactory.getLogger(FeignController.class);

    @Autowired
    RefactorHelloService refactorHelloService;
    @Autowired
    HelloService1 helloService1;
    @RequestMapping(value="/feign-consumer",method= RequestMethod.GET)
    public String feignConsumer(HttpServletRequest hsr){
        logger.info("feign...");
        return refactorHelloService.hello();
    }
    @RequestMapping(value="/feign-consumer2",method= RequestMethod.GET)
    public String feignConsumer2(HttpServletRequest hsr){
        logger.info("feign...");

        StringBuffer sb = new StringBuffer();
        sb.append(helloService1.hello()).append("<br>").
                append(helloService1.hello2("DZD")).append("<br>").
                append(helloService1.hello3("DSD","13")).append("<br>").
                append(helloService1.hello4(new cn.com.feign.domain.User("DDD","12")));
        return sb.toString();
    }

    @RequestMapping(value="/feign-consumer3",method= RequestMethod.GET)
    public String feignConsumer3(HttpServletRequest hsr){
        logger.info("feign...");

        StringBuffer sb = new StringBuffer();
        sb.append(refactorHelloService.hello()).append("<br>").
                append(refactorHelloService.hello("mm")).append("<br>").
                append(refactorHelloService.hello("oo","13")).append("<br>").
                append(refactorHelloService.hello(new cn.com.fegin.user.User("yy","12")));
        return sb.toString();
    }
}
