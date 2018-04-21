package cn.com.zuul.dynamicFilter.service;

import java.util.List;

import cn.com.zuul.dynamicFilter.model.ZuulRouteVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

public interface ZuulRouteService {
	/**
	 * 
	 *<p>Title:addZuulRouteVo</p>
	 *<p>Description:添加路由信息</p>
	 *<p>param @param vo</p>
	 *<p>return void</p>
	 *<p>Company:南天信息工程</p>
	 * @author Acmen
	 * @date 2018年1月16日 下午5:33:03
	 *
	 */
	public void addZuulRouteVo(ZuulRouteVo vo);
	/**
	 * 
	 *<p>Title:getZuulRouteVos</p>
	 *<p>Description:从数据库表读取所有zuul.route信息</p>
	 *<p>param @return</p>
	 *<p>return List<ZuulRouteVo></p>
	 *<p>Company:南天信息工程</p>
	 * @author Acmen
	 * @date 2018年1月16日 下午5:34:18
	 *
	 */
	public List<ZuulRouteVo> getZuulRouteVos();
	/**
	 * 
	 *<p>Title:updateZuulRouteVo</p>
	 *<p>Description:修改路由信息</p>
	 *<p>param @param id</p>
	 *<p>return void</p>
	 *<p>Company:南天信息工程</p>
	 * @author Acmen
	 * @date 2018年1月16日 下午5:35:24
	 *
	 */
	public void updateZuulRouteVo(String apiName, String path);
	/**
	 * 
	 *<p>Title:getZuulRouteVo</p>
	 *<p>Description:获取某个路由规则信息</p>
	 *<p>param @param id
	 *<p>param @return</p>
	 *<p>return ZuulRouteVo</p>
	 *<p>Company:南天信息工程</p>
	 * @author Acmen
	 * @date 2018年1月16日 下午5:36:58
	 *
	 */
	
	public ZuulRouteVo getZuulRouteVo(String apiName);
	/**
	 * 
	 *<p>Title:deleteZuulRouteVo</p>
	 *<p>Description:删除</p>
	 *<p>param @param serviceId</p>
	 *<p>return void</p>
	 *<p>Company:南天信息工程</p>
	 * @author Acmen
	 * @date 2018年1月26日 下午6:03:55
	 *
	 */
	public void deleteZuulRouteVo(@Param("serviceId") String serviceId);
}
