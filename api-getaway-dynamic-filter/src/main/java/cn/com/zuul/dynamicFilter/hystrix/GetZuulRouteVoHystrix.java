package cn.com.zuul.dynamicFilter.hystrix;

import cn.com.zuul.dynamicFilter.model.ZuulRouteVo;
import cn.com.zuul.dynamicFilter.service.impl.ZuulRouteServiceImpl;
import com.netflix.hystrix.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 *<p>Title:GetZuulRouteVoHystrix</p>
 *<p>Description:并发接口 使用hystrix 线程池来对共性资源进行限制。</p>
 *<p>Company:南天信息工程</p>
 * @author Acmen
 * @date 2018年3月7日 上午9:58:42
 *	hystrix 会为每个实现方法的接口去创建一个线程池 去多服务的隔离。
 */
public class GetZuulRouteVoHystrix extends HystrixCommand<ZuulRouteVo> {
	private static final Logger log = Logger.getLogger(GetZuulRouteVoHystrix.class);
	@Autowired
	private ZuulRouteServiceImpl zuulRouteServiceImpl;
	private String appName;

	public GetZuulRouteVoHystrix(String appName) {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ZuulRouteService"))
					.andCommandKey(HystrixCommandKey.Factory.asKey("GetZuulRouteVoHystrix"))
					.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("GetZuulRouteVoPool"))
					.andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()//开始为这个接口设置线程池配置
					.withCoreSize(10)//
					.withAllowMaximumSizeToDivergeFromCoreSize(true)
					.withMaxQueueSize(50)
					.withKeepAliveTimeMinutes(1)
					.withQueueSizeRejectionThreshold(100)
							)
				
				);
		this.appName = appName;
	}
	
	@Override
	protected ZuulRouteVo run() throws Exception {
		log.info("【【【【【  GetZuulRouteVoHystrix RUNNING ... 】】】】】】");
		ZuulRouteVo vo = zuulRouteServiceImpl.getZuulRouteVo(appName);
		log.info("【【【【【  GetZuulRouteVoHystrix query  DATA ... 】】】】】】"+vo);
		return vo;
	}
	protected ZuulRouteVo getFallback() {
		ZuulRouteVo vo = new ZuulRouteVo();
		vo.setApiName("服务降级处理");
		return vo;
	}
	
	
}
