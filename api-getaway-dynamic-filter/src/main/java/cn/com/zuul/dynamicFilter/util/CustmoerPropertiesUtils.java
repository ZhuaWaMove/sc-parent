package cn.com.zuul.dynamicFilter.util;

import cn.com.zuul.dynamicFilter.config.CustRateLimitProperties;
import org.jetbrains.annotations.Contract;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GL-shala on 2018/3/29.
 */
@Component
public class CustmoerPropertiesUtils {

    public static final Map custMap = new HashMap();

    public static void getProperties(Object o){
        if(o instanceof CustRateLimitProperties){
            CustRateLimitProperties custProperties = (CustRateLimitProperties) o;
            custMap.put("sysCode",custProperties.getSysCode());
            custMap.put("sysLimitFlag",custProperties.getSysLimitFlag());
            custMap.put("sysRefreshTime",custProperties.getSysRefreshTime());
            custMap.put("sysLimitCount",custProperties.getSysLimitCount());
            custMap.put("sysMaxQuota",custProperties.getSysMaxQuota());

            custMap.put("maxUseRateCPU",custProperties.getMaxUseRateCPU());
            custMap.put("maxUseRateMemory",custProperties.getMaxUseRateMemory());
            custMap.put("serverPreLimitFlag",custProperties.getServerPreLimitFlag());
        }else{
           //nothing....
        }

    }


}
