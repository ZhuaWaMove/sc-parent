package cn.com.zuul.dynamicFilter.hystrix;

import cn.com.zuul.dynamicFilter.model.ZuulRouteVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
/**
 * 
 *<p>Title:RedisCacheHystrix</p>
 *<p>Description:针对操作进行服务降级处理</p>
 *<p>Company:南天信息工程</p>
 * @author Acmen
 * @date 2018年3月6日 下午4:16:09
 *
 */
public class RedisCacheHystrix extends HystrixCommand<ZuulRouteVo> {
	public static final Logger log = Logger.getLogger(RedisCacheHystrix.class);
	@Autowired
	private StringRedisTemplate template;
		
	public static final HystrixCommandKey COMMAND_KEY = HystrixCommandKey.Factory.asKey("RedisCacheHystrix");

		private final String appName;
		//设置服务接口为hystrix 类型
		public RedisCacheHystrix(String appName) {
			//用于对不同依赖的线程池/信号区分
			super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("RedisCacheGroup"))
					//command——key 区分服务调用依赖 
				 .andCommandKey(COMMAND_KEY)
				 .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
				 .withExecutionTimeoutInMilliseconds(100)
				 .withCircuitBreakerRequestVolumeThreshold(1000)
				 .withCircuitBreakerErrorThresholdPercentage(70)
				 .withCircuitBreakerSleepWindowInMilliseconds(60*1000))
					);
			// TODO Auto-generated constructor stub
			this.appName = appName;
		}
		//从缓存中读取数据
		@Override
		protected ZuulRouteVo run() throws Exception {
			System.out.println("进入 REDISCACHEHYSTRIX RUN() 方法");
			String key = "1234";
			log.info("REDISCACHEHYSTRIX--------"+ key);
			String object = template.opsForValue().get(key);
			log.info("REDISCACHEHYSTRIX--------"+ object);
			ZuulRouteVo db2 = JSONObject.parseObject(object,new TypeReference<ZuulRouteVo>(){});
			return db2;
		}
		
		protected ZuulRouteVo getFallback() {
			ZuulRouteVo vo = new ZuulRouteVo();
			vo.setApiName("服务降级处理");
			return vo;
		}
}
