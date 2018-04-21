package cn.com.zuul.dynamicRoute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;

/**
 * Created by new on 2018/3/3.
 */
@EnableZuulProxy
@SpringCloudApplication
public class ApplicationDynamicRoute {
    public static void main(String[] args) {
        new SpringApplication().run(ApplicationDynamicRoute.class);
    }

    @Bean
    @RefreshScope //将zuul的配置内容动态化
    @ConfigurationProperties("zuul")
    public ZuulProperties zuulProperties(){
        return new ZuulProperties();
    }

}
