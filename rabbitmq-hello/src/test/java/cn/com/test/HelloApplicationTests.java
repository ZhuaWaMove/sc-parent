package cn.com.test;

import cn.com.rabbitmq.HelloApplication;
import cn.com.rabbitmq.sender.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by new on 2017/12/29.
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HelloApplication.class)
public class HelloApplicationTests {

   @Autowired
    private Sender sender;

   @Test
   public void test(){
       for (int i = 0; i <100000 ; i++) {
           sender.sender();
       }
   }
}

