package filter.pre;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.util.Map;

/**
 * Created by GL-shala on 2018/3/15.
 */
public class ARateLimiterFilter extends ZuulFilter {

    Logger logger = LoggerFactory.getLogger(ARateLimiterFilter.class);
    private final RateLimiter rateLimiter = RateLimiter.create(1000.0);
    private  Map<String, RateLimiter> map = Maps.newConcurrentMap();
    private static Integer count = 0;
    private static Integer count1 = 0;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }


    @Override
    public int filterOrder() {
        return 600;
    }


    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        return null;
    }


//    @Override
//    public Object run() {
//
//        try{
//            HttpServletResponse response =  RequestContext.getCurrentContext().getResponse();
//            logger.info("执行了RateLimiterFilter......");
//            String key = null;
//
//            // 对于service格式的路由，走RibbonRoutingFilter
//            String serviceId = (String) RequestContext.getCurrentContext().get(FilterConstants.SERVICE_ID_KEY);
//            if (serviceId != null) {
//                key = serviceId;
//               Object o = map.putIfAbsent(serviceId, RateLimiter.create(20.0));
//                count1++;
//            } else {// 如果压根不走RibbonRoutingFilter，则认为是URL格式的路由
//                // 对于URL格式的路由，走SimpleHostRoutingFilter
//                URL routeHost = RequestContext.getCurrentContext().getRouteHost();
//                if (routeHost != null) {
//                    String url = routeHost.toString();
//                    key = url;
//                    Object o = map.putIfAbsent(url, RateLimiter.create(20.0));
//                    count++;
//                }
//            }
//            RateLimiter rateLimiter = map.get(key);
//
//            Boolean tryAcquire =  rateLimiter.tryAcquire();
//            if (!tryAcquire) {
//                HttpStatus httpStatus = HttpStatus.TOO_MANY_REQUESTS;
//                response.setContentType(MediaType.TEXT_PLAIN_VALUE);
//                response.setStatus(httpStatus.value());
//                response.getWriter().append(httpStatus.getReasonPhrase());
//                response.reset();
//                RequestContext.getCurrentContext().setSendZuulResponse(false);
//                throw new ZuulException(httpStatus.getReasonPhrase(), httpStatus.value(), httpStatus.getReasonPhrase());
//            }
//        } catch (Exception e) {
//            ReflectionUtils.rethrowRuntimeException(e);
//        }
//        logger.info("请求次数count "+count+" count1 "+ count1);
//        return null;
//
//    }
}
