package cn.com.zuul.dynamicFilter.service.impl;

import java.util.List;

import javax.annotation.Resource;

import cn.com.zuul.dynamicFilter.mapper.ZuulRouteMapper;
import cn.com.zuul.dynamicFilter.model.ZuulRouteVo;
import cn.com.zuul.dynamicFilter.service.ZuulRouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;



import redis.clients.jedis.JedisPool;
@Service
@CacheConfig(cacheNames="zuulRoute")
public class ZuulRouteServiceImpl implements ZuulRouteService {
	private static final Logger log = LoggerFactory.getLogger(ZuulRouteServiceImpl.class);

	public void addZuulRouteVo(ZuulRouteVo vo) {
		zuulRouteMapper.addZuulRouteVo(vo);
	}


	@Resource
	@Autowired
	private ZuulRouteMapper zuulRouteMapper;

	public List<ZuulRouteVo> getZuulRouteVos() {
		List<ZuulRouteVo> list = zuulRouteMapper.getZuulRouteVos();
		return list;
	}
	public void updateZuulRouteVo(String apiName,String path) {
		zuulRouteMapper.updateZuulRouteVo(apiName, path);
	}
	@Cacheable(key="'api_name'+#apiName")
	public ZuulRouteVo getZuulRouteVo(String  apiName) {
		ZuulRouteVo vo = zuulRouteMapper.getZuulRouteVo(apiName);
		return vo;
	}
	@Override
	public void deleteZuulRouteVo(String serviceId) {
		zuulRouteMapper.deleteZuulRouteVo(serviceId);
	}
}
