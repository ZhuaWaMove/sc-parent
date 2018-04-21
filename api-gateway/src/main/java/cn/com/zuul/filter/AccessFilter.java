package cn.com.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.apache.commons.lang.StringUtils.isBlank;

/**
 * Created by new on 2018/2/24.
 */
//@Component
public class AccessFilter extends ZuulFilter {
    public static Logger logger = LoggerFactory.getLogger(AccessFilter.class);
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request;
        request = ctx.getRequest();
        logger.info("send {} request to {}",request.getMethod(),request.getRequestURI().toString());
        logger.info("cookie:"+request.getCookies());
        String accessToken = request.getParameter("accessToken");
        if(isBlank(accessToken)){
           logger.warn("access token is null");
           ctx.setSendZuulResponse(false);
           ctx.setResponseStatusCode(401);
           ctx.setResponseBody("access token is null");
            return ctx;
        }
        logger.info("access token is ok!");
        return null;
    }
}
