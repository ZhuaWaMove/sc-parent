package cn.com.zuul.dynamicFilter.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by GL-shala on 2018/3/8.
 */
@Component
@RabbitListener(queues = "hello")
public class Receiver {

    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    @RabbitHandler
    public void receiverFromRabbitHello( String args){
        logger.info("接收到rabbit发送的消息{}",args);
    }
}
