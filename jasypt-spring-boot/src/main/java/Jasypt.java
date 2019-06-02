import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by GL-shala on 2018/7/13.
 */
@ComponentScan
@SpringBootApplication
@EnableEncryptableProperties
public class Jasypt {

    public static void main(String[] args) {
            SpringApplication.run(Jasypt.class,args);
    }

}
