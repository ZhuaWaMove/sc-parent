package cn.com.zuul.dynamicFilter.config;

import com.google.common.collect.Lists;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.properties.RateLimitProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by GL-shala on 2018/3/27.
 */
@Component
@ConfigurationProperties("zuul.custratelimit")
public class CustRateLimitProperties{

//    zuul.custratelimit.sys-code=888888
//    zuul.custratelimit.sys-limit-flag=true
//    zuul.custratelimit.sys-limit-count=2400
//    zuul.custratelimit.sys-max-quota=2400
//    zuul.custratelimit.sys-refresh-time=3
//    zuul.custratelimit.service-pre-limit-flag=true
//    zuul.custratelimit.max-use-rate-c-p-u=90
//    zuul.custratelimit.max-use-rate-memory=80

    //限流开关  关联系统
    private boolean sysLimitFlag;
    //服务器
    private boolean serverPreLimitFlag;
     //外系统代码
    private String sysCode;

    //限流单位时间
    private Long sysRefreshTime;
    //单位时间内所有请求的时间总和的最大时间
    private Long sysMaxQuota;
    //单位时间最大流量
    private Long sysLimitCount;

    //服务器CPU使用率
    private Long maxUseRateCPU;
    //服务器内存使用率
    private Long maxUseRateMemory;

    public boolean getSysLimitFlag() {
        return sysLimitFlag;
    }

    public void setSysLimitFlag(boolean sysLimitFlag) {
        this.sysLimitFlag = sysLimitFlag;
    }

    public boolean getServerPreLimitFlag() {
        return serverPreLimitFlag;
    }

    public void setServerPreLimitFlag(boolean serverPreLimitFlag) {
        this.serverPreLimitFlag = serverPreLimitFlag;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public Long getSysRefreshTime() {
        return sysRefreshTime;
    }

    public void setSysRefreshTime(Long sysRefreshTime) {
        this.sysRefreshTime = sysRefreshTime;
    }

    public Long getSysMaxQuota() {
        return sysMaxQuota;
    }

    public void setSysMaxQuota(Long sysMaxQuota) {
        this.sysMaxQuota = sysMaxQuota;
    }

    public Long getSysLimitCount() {
        return sysLimitCount;
    }

    public void setSysLimitCount(Long sysLimitCount) {
        this.sysLimitCount = sysLimitCount;
    }

    public Long getMaxUseRateCPU() {
        return maxUseRateCPU;
    }

    public void setMaxUseRateCPU(Long maxUseRateCPU) {
        this.maxUseRateCPU = maxUseRateCPU;
    }

    public Long getMaxUseRateMemory() {
        return maxUseRateMemory;
    }

    public void setMaxUseRateMemory(Long maxUseRateMemory) {
        this.maxUseRateMemory = maxUseRateMemory;
    }

    @Override
    public String toString() {
        return "CustRateLimitProperties{" +
                "sysLimitFlag=" + sysLimitFlag +
                ", serverPreLimitFlag=" + serverPreLimitFlag +
                ", sysCode='" + sysCode + '\'' +
                ", sysRefreshTime=" + sysRefreshTime +
                ", sysMaxQuota=" + sysMaxQuota +
                ", sysLimitCount=" + sysLimitCount +
                ", maxUseRateCPU=" + maxUseRateCPU +
                ", maxUseRateMemory=" + maxUseRateMemory +
                '}';
    }
}
