package cn.com.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

import javax.swing.*;

/**
 * Created by new on 2017/12/27.
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient //开启注册
public class ConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class,args);
    }
}
