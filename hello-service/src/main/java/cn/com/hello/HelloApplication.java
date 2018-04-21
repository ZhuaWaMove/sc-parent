package cn.com.hello;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.InstanceInfo.Builder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * Created by new on 2017/12/24.
 */
@EnableEurekaClient
@SpringBootApplication
public class HelloApplication {
    public static void main(String[] args) {
       SpringApplication.run(HelloApplication.class,args);
    }


//    @Bean
//    CommandLineRunner runner(DiscoveryClient dc){
//        return args -> {
//            dc.getInstances("").forEach(si -> System.out.println((String.format(
//                    "Found %s %s:%s", si.getServiceId(), si.getHost(), si.getPort()))));
//        };
//
//    }
}
