package cn.com.trace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by GL-shala on 2018/3/7.
 */
@RestController
@EnableEurekaClient
@SpringBootApplication
public class Trace2Application {

    public static final Logger logger = LoggerFactory.getLogger(Trace2Application.class);
    private static Integer count = 0;
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
       return new RestTemplate();
    }

    @RequestMapping(value = "/trace-2",method = RequestMethod.GET)
    public String trace(){
        logger.info("===call trace-2==="+count++);
        return "Trace-2";
    }
    public static void main(String[] args) {
        SpringApplication.run(Trace2Application.class,args);
    }
}
