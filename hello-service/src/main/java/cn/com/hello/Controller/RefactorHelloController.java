package cn.com.hello.Controller;

import cn.com.fegin.user.User;
import cn.com.fegin.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by GL-shala on 2018/3/9.
 */
@RestController
public class RefactorHelloController implements HelloService {

    private static final Logger logger = LoggerFactory.getLogger(RefactorHelloController.class);
    @Override
    public String hello() {
        return "hello";
    }

    @Override
    public String hello(@RequestParam("name") String name) {
        logger.info("请求参数：name");
        return "hello-service 方法hello"+" 请求参数：name="+name;
    }

    @Override
    public String hello(@RequestHeader("name") String name,@RequestHeader("age") String age) {
        logger.info("请求参数：user");
        return "hello-service 方法hello 请求参数：user="+new cn.com.hello.domain.User(name,age);
    }

    @Override
    public String hello(@RequestBody User user) {
        logger.info("请求参数：name  age");
        return "hello-service 方法 hello 请求参数：name="+user.getName()+" age="+user.getAge();
    }
}
