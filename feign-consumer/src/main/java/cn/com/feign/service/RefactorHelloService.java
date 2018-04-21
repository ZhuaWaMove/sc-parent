package cn.com.feign.service;

import cn.com.fegin.service.HelloService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by GL-shala on 2018/3/9.
 */
@FeignClient("hello-service")
public interface RefactorHelloService extends HelloService {
}
