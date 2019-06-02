import config.CxfConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by GL-shala on 2018/4/17.
 */
@SpringBootApplication
//@EnableConfigurationProperties({CxfConfig.class})
@ComponentScan( "config")
public class WsServerApp {

    public static void main(String[] args) {
        SpringApplication.run(WsServerApp.class,args);
    }
}
