package cn.com.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by GL-shala on 2018/7/14.
 */
@SpringBootApplication
@MapperScan("cn.com.mybatis")
public class MytaisApp {

    public static void main(String[] args) {
        SpringApplication.run(MytaisApp.class,args);
    }
}
