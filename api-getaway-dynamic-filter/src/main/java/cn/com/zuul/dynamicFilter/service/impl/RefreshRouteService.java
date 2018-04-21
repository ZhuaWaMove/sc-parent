package cn.com.zuul.dynamicFilter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class RefreshRouteService {
	@Autowired
	private ApplicationEventPublisher publisher;
	@Autowired
	private RouteLocator routeLocator;
	/**
	 * 
	 *<p>Title:refreshRoute</p>
	 *<p>Description: 更改后的配置信息需要进行路由刷新，做crud操作的时候</p>
	 *<p>param </p>
	 *<p>return void</p>
	 *<p>Company:南天信息工程</p>
	 * @author Acmen
	 * @date 2018年1月16日 下午8:23:14
	 *
	 */
	public void refreshRoute() {
		RoutesRefreshedEvent routes = new RoutesRefreshedEvent(routeLocator);
		publisher.publishEvent(routes);
	}
}
