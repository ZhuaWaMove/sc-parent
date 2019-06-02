package config;

import org.apache.cxf.Bus;
import javax.xml.ws.Endpoint;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;
import service.UserService;
import service.impl.UserServiceImpl;

/**
 * Created by GL-shala on 2018/4/17.
 */
@Configuration
public class CxfConfig {

    //    @Autowired
    //    private Bus bus;
    @Bean
    public ServletRegistrationBean dispatcherServlet(){
        logger.info("creat ServletRegistrationBean bean");
        return new ServletRegistrationBean(new CXFServlet(),"/soap/*");
    }


    @Bean(name = Bus.DEFAULT_BUS_ID)
    public Bus springBus(){
        return new SpringBus();
    }

    private static final Logger logger = LoggerFactory.getLogger(CxfConfig.class);
    @Bean
    public UserService userService() {
        logger.info("creat UserService bean");
        return new UserServiceImpl();
    }
    //此处要注意导入正取的Endpoint、EndpointImpl包
    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), userService());
        endpoint.publish("/user");
        logger.info("creat Endpoint bean");
        return endpoint;
    }
}
