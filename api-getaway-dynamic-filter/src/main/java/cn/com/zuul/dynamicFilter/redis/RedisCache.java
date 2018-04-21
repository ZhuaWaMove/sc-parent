package cn.com.zuul.dynamicFilter.redis;

import cn.com.commen.util.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
@Component("redisCache")
public class RedisCache {
	@Autowired
	private JedisPool jedisPool;
	
	/**
	 * 
	 *<p>Title:getDateFromRedis</p>
	 *<p>Description:从redis缓存中获取数据</p>
	 *<p>param @param rediskey
	 *<p>param @return</p>
	 *<p>return Object</p>
	 *<p>Company:南天信息工程</p>
	 * @author Acmen
	 * @date 2018年2月28日 上午11:25:41
	 *
	 */
	public Object getDataFromRedis(String rediskey) {
		Jedis jedis = jedisPool.getResource();
		byte[] byteArray = jedis.get(rediskey.getBytes());
		if(byteArray!=null) {
			//将从redis缓存中获取的数据发序列化成对象
			Object object = SerializeUtil.unserialize(byteArray);
			return object;
		}
		return null;
	}
	
	/**
	 * 
	 *<p>Title:saveDataToRedis</p>
	 *<p>Description:保存数据到redis缓存中</p>
	 *<p>param @param rediskey
	 *<p>param @param object
	 *<p>param @return</p>
	 *<p>return String</p>
	 *<p>Company:南天信息工程</p>
	 * @author Acmen
	 * @date 2018年2月28日 上午11:27:27
	 *
	 */
	public String saveDataToRedis(String rediskey,Object object) {
		//将对象转换成byte字节
		byte[] byteArray = SerializeUtil.serialize(object);
		String data = jedisPool.getResource().set(rediskey.getBytes(), byteArray);
		return data;
	}
}
