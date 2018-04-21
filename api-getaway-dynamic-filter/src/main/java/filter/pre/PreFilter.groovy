package filter.pre

import cn.com.zuul.dynamicFilter.config.CustRateLimitProperties
import cn.com.zuul.dynamicFilter.config.FilterConfiguration
import cn.com.zuul.dynamicFilter.util.CustmoerPropertiesUtils
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.properties.RateLimitProperties
import com.netflix.zuul.ZuulFilter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants
import org.springframework.context.annotation.Bean

/**
 * Created by new on 2018/3/4.
 */
public class PreFilter extends ZuulFilter {


    Logger logger = LoggerFactory.getLogger(PreFilter.class);
    @Override
    public  String filterType() {
        return FilterConstants.PRE_TYPE;
    }


    @Override
    public int filterOrder() {
        return 500;
    }


    @Override
    public boolean shouldFilter() {
        return true;
    }


    @Override
    public Object run() {
        logger.info("执行了PreFilter......");
        ZuulProperties.HystrixSemaphore zh = new ZuulProperties.HystrixSemaphore();
        Integer max =  zh.getMaxSemaphores();
        ZuulProperties zp = new ZuulProperties();
        ZuulProperties.Host s = zp.getHost();
        ZuulProperties.HystrixSemaphore dd =  zp.getSemaphore();

        RateLimitProperties rp = new RateLimitProperties();
        String s1 = rp.toString();
        logger.info("限流参数："+s1);
        logger.info("自定义参数："+CustmoerPropertiesUtils.custMap.toString());
        return null;

    }
}
