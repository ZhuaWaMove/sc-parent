package cn.com.jasypt.test;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by GL-shala on 2018/7/13.
 */

public class JasyptTest {


    @Autowired
    StringEncryptor stringEncryptor;
    @Test
    public void test(){

        System.out.println(stringEncryptor.encrypt("123456"));
    }
}
