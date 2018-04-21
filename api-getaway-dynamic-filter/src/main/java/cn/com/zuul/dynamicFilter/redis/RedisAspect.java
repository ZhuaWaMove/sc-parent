package cn.com.zuul.dynamicFilter.redis;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cn.com.zuul.dynamicFilter.model.ZuulRouteVo;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
@Component
@Aspect
public class RedisAspect {
	private static final Logger log = Logger.getLogger(RedisAspect.class);
	@Autowired
	@Qualifier("redisCache")//如果spring容器找到相同的bean 先执行这个类
	private RedisCache redisCache;
	@Autowired
	private KeyGenerator keyGenerator;
	@Autowired
	private StringRedisTemplate template;
	/**
	 * 
	 *<p>Title:pointcutMethod</p>
	 *<p>Description:定义切点</p>
	 *<p>param </p>
	 *<p>return void</p>
	 *<p>Company:南天信息工程</p>
	 * @author Acmen
	 * @date 2018年2月28日 上午11:43:53
	 *
	 */
	@Pointcut("execution(* cn.com.zuul.dynamicFilter.service.*.getZuulRouteVo(..))")
	public void pointcutMethod() {
	}
	@Around(value = "pointcutMethod()")
	public Object ServiceAspect(ProceedingJoinPoint joinPoint) {
		//这里我们把key写死方便测试
		List<Object> list = new ArrayList<>();
		
		String rediskey = "1234";
		/*Object db = redisCache.getDataFromRedis(rediskey);*/
		String db = template.opsForValue().get(rediskey);
		if(db!=null) {
			log.info("*******从redis中查到了数据*********");
			log.info("查询出来的key："+rediskey);
			log.info("查询出来的value值为"+db.toString());
			ZuulRouteVo db2 = JSONObject.parseObject(db,new TypeReference<ZuulRouteVo>(){});
			return db2;
		}
		log.info("********开始从数据库查询数据********");
		try {
			 Object db1 =  joinPoint.proceed();//启动切面方法获取从数据库查询出来的数据
			 if(db1!=null) {
			 String jsonObect = JSONObject.toJSONString(db1);
			template.opsForValue().set(rediskey,jsonObect);
			log.info("*********成功保存到了redis缓存中******");
			log.info("保存的数据信息key："+rediskey);
			log.info("保存的值value："+jsonObect);
			return db1;
			 }
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}
}
