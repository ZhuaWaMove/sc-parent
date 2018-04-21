package cn.com.config.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by new on 2017/12/27.
 */
@RefreshScope
@RestController
public class TestController {

   @Value("${from}")
   private String from;

    @Autowired
    private Environment environment;

    @RequestMapping("/from")
    public String from(){
        return environment.getProperty("from","undefined");

    }
    @RequestMapping("/from1")
    public String from1(){
        return this.from;

    }
}
