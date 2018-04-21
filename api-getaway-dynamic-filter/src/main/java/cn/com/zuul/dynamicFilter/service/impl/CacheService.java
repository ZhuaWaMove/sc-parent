package cn.com.zuul.dynamicFilter.service.impl;

import cn.com.zuul.dynamicFilter.hystrix.RedisCacheHystrix;
import cn.com.zuul.dynamicFilter.model.ZuulRouteVo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;


@Service("cacheService")
public class CacheService {
	public static final Logger log = Logger.getLogger(CacheService.class);
	public ZuulRouteVo getZuulRouteVo(String  apiName) {
		RedisCacheHystrix cache = new RedisCacheHystrix(apiName);
		ZuulRouteVo vo = cache.execute();
		log.info("cacheService running .....");
		log.info("cacheService execute is data-----"+vo);
		return vo;
	}
}
