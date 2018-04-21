package cn.com.zuul.dynamicFilter.controller;

import cn.com.zuul.dynamicFilter.config.CustRateLimitProperties;
import cn.com.zuul.dynamicFilter.config.FilterConfiguration;
import cn.com.zuul.dynamicFilter.util.CustmoerPropertiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.web.ZuulController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by GL-shala on 2018/3/5.
 */
@RefreshScope
@RestController
public class DynamicFilterController extends ZuulController{

    @Value(value = "${zuul}")
    private String zuul;
    @Autowired
    CustRateLimitProperties custRateLimitProperties;

    @RequestMapping(value = "/local/hello",method = RequestMethod.GET)
    public String zulRoutes(){
        CustRateLimitProperties zcrp = new CustRateLimitProperties();
        return this.zuul;
    }
    @RequestMapping(value = "/refreshCustProperties",method = RequestMethod.GET)
    public String refreshCustProperties(){
       try{
           CustmoerPropertiesUtils.getProperties(custRateLimitProperties);
       }catch (Exception e){
          throw  new RuntimeException(e);
       }
        return "refresh ok...";
    }
}
