package cn.com.raibbon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
public class Trace1Application {

    private final Logger logger = LoggerFactory.getLogger(Trace1Application.class);

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @RequestMapping(value = "/trace-1",method = RequestMethod.GET)
    public String trace_1(){
        logger.info("===call trace_1===");
        return restTemplate().getForEntity("http://trace-2/trace-2",String.class).getBody();
    }
    public static void main(String[] args) {
        SpringApplication.run(Trace1Application.class,args);
    }
}
