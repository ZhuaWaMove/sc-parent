package cn.com.zuul.dynamicFilter;

import cn.com.zuul.dynamicFilter.config.CustRateLimitProperties;
import cn.com.zuul.dynamicFilter.config.FilterConfiguration;
import cn.com.zuul.dynamicFilter.util.CustmoerPropertiesUtils;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.DefaultRateLimitKeyGenerator;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.RateLimitKeyGenerator;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.properties.RateLimitProperties;
import com.netflix.zuul.FilterFileManager;
import com.netflix.zuul.FilterLoader;
import com.netflix.zuul.groovy.GroovyCompiler;
import com.netflix.zuul.groovy.GroovyFileFilter;
import filter.pre.ARateLimiterFilter;
import filter.pre.BusSysRateLimit;
import filter.pre.ServerRateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by new on 2018/3/3.
 */
@EnableZuulProxy
@EnableConfigurationProperties({FilterConfiguration.class, CustRateLimitProperties.class})
@SpringCloudApplication
@MapperScan("cn.com.zuul.dynamicFilter.mapper")
@ComponentScan("cn.com.zuul")
@EnableCaching
public class ApplicationDynamicFilter {

    private static Logger logger = LoggerFactory.getLogger(ApplicationDynamicFilter.class);
    public static void main(String[] args) {
        new SpringApplication().run(ApplicationDynamicFilter.class,args);
        logger.info("ApplicationDynamicFilter start.......");
    }

    @Bean
    @RefreshScope //将zuul的配置内容动态化
    @ConfigurationProperties("zuul")
    public ZuulProperties zuulProperties(CustRateLimitProperties custRateLimitProperties){

        CustmoerPropertiesUtils.getProperties(custRateLimitProperties);
        logger.info("将zuul的配置内容动态化......."+CustmoerPropertiesUtils.custMap.toString());
        return new ZuulProperties();
    }

    @Bean
    public CustRateLimitProperties custRateLimitProperties(CustRateLimitProperties custRateLimitProperties){
        CustmoerPropertiesUtils.getProperties(custRateLimitProperties);
        logger.info("创建CustRateLimitProperties Bean ,初始化自定义属性custMap......."+custRateLimitProperties.getSysCode());
        return custRateLimitProperties;
    }

    @Bean
    public ARateLimiterFilter aRateLimiterFilter(){
        logger.info("创建aRateLimiterFilter.......");
        return new ARateLimiterFilter();
    }
    @Bean
    public BusSysRateLimit busSysRateLimit(){
        return new BusSysRateLimit();
    }
    @Bean
    public ServerRateLimiter serverRateLimiter(){
        return new ServerRateLimiter();
    }

    @Bean
    public RateLimitKeyGenerator rateLimitKeyGenerator(final RateLimitProperties properties) {
        return new DefaultRateLimitKeyGenerator(properties) {
            @Override
            public String key(HttpServletRequest request, Route route, RateLimitProperties.Policy policy) {
                return super.key(request, route, policy) + ":" + request.getMethod();
            }
        };
    }

    @Bean
    public FilterLoader filterLoader(FilterConfiguration filterConfiguration){

        FilterLoader filterLoader = FilterLoader.getInstance();
        filterLoader.setCompiler(new GroovyCompiler());
        try{
            FilterFileManager.setFilenameFilter(new GroovyFileFilter());
            FilterFileManager.init(filterConfiguration.getInterval(),
                    filterConfiguration.getRoot()+"/pre", filterConfiguration.getRoot()+"/post");
        }catch (Exception e){
            throw  new RuntimeException();
        }
        return filterLoader;
    }
}
