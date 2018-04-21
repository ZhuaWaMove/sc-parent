package cn.com.rabbitmq.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by new on 2017/12/29.
 * 消息生产者Sender
 */
@Component
public class Sender {

    @Autowired
    AmqpTemplate amqpTemplate;

    public void sender(){

        String context = "hello："+new Date();
        System.out.println("Sender: "+context);
        this.amqpTemplate.convertAndSend("hello",context);
    }
}
