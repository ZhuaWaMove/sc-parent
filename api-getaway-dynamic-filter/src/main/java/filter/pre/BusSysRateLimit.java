package filter.pre;

import cn.com.zuul.dynamicFilter.util.CustmoerPropertiesUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.constants.ZuulConstants;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * Created by GL-shala on 2018/3/30.
 */
public class BusSysRateLimit extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 700;
    }

    @Override
    public boolean shouldFilter() {

        Boolean sysLimitFlag = (Boolean) CustmoerPropertiesUtils.custMap.get("sysLimitFlag");
        String sysCode = (String) CustmoerPropertiesUtils.custMap.get("sysCode");
        Long sysRefreshTime = (Long) CustmoerPropertiesUtils.custMap.get("sysRefreshTime");
        Long sysMaxQuota = (Long) CustmoerPropertiesUtils.custMap.get("sysMaxQuota");
        Long sysLimitCount = (Long) CustmoerPropertiesUtils.custMap.get("sysLimitCount");

        Boolean servicePreLimitFlag = (Boolean) CustmoerPropertiesUtils.custMap.get("servicePreLimitFlag");
        Long maxUseRateCPU = (Long) CustmoerPropertiesUtils.custMap.get("maxUseRateCPU");
        Long maxUseRateMemory = (Long) CustmoerPropertiesUtils.custMap.get("maxUseRateMemory");

        if(sysLimitFlag){

        }
        return false;
    }

    @Override
    public Object run() {
        return null;
    }
}
