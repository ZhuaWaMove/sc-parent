package cn.com.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by new on 2017/12/29.
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue hello(){
        return new Queue("hello");
    }


}
