package cn.com.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Created by new on 2018/3/2.
 */
@Component
public class ThrowExceptionFilter extends ZuulFilter {

    private static Logger logger = Logger.getLogger(ThrowExceptionFilter.class);
    /**
     * to classify a filter by type. Standard types in Zuul are "pre" for pre-routing filtering,
     * "route" for routing to an origin, "post" for post-routing filters, "error" for error handling.
     * We also support a "static" type for static responses see  StaticResponseFilter.
     * Any filterType made be created or added and run by calling FilterProcessor.runFilters(type)
     *
     * @return A String representing that type
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * filterOrder() must also be defined for a filter. Filters may have the same  filterOrder if precedence is not
     * important for a filter. filterOrders do not need to be sequential.
     *
     * @return the int order of a filter
     */
    @Override
    public int filterOrder() {
        return 0;// 优先级为0，数字越大，优先级越低
    }

    /**
     * a "true" return from this method means that the run() method should be invoked
     *
     * @return true if the run() method should be invoked. false will not invoke the run() method
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * if shouldFilter() is true, this method will be invoked. this method is the core method of a ZuulFilter
     *
     * @return Some arbitrary artifact may be returned. Current implementation ignores it.
     */
    @Override
    public Object run() {
        logger.info("This is a pre filter , it will throw a RuntimeException!");
        doSomething();
        return null;
    }
    private void doSomething(){
        throw  new RuntimeException("Exist some error...");
    }
}
