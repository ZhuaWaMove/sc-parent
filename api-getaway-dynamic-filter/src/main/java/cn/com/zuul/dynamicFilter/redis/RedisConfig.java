package cn.com.zuul.dynamicFilter.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import java.lang.reflect.Method;

@Configuration
@ConfigurationProperties("spring.redis")
public class RedisConfig extends CachingConfigurerSupport{

	private static final Logger log = LoggerFactory.getLogger(RedisConfig.class);
	
	@Value("${spring.redis.host}")
	private String host;
	
	@Value("${spring.redis.password}")
	private String password;
	
	
	@Value("${spring.redis.database}")
	private Integer database;
	
	@Value("${spring.redis.port}")
	private Integer port;

	@Value("${spring.redis.pool.max-active}")
	private Integer maxActive;
	
	@Value("${spring.redis.pool.max-wait}")
	private Integer maxWait;
	
	@Value("${spring.redis.pool.max-idle}")
	private Integer maxIdle;
	
	@Value("${spring.redis.pool.min-idle}")
	private Integer minIdle;
	
	@Value("${spring.redis.timeout}")
	private Integer timeout;
	
	
	
	/**
	 * 
	 *<p>Title:getJedisPool</p>
	 *<p>Description:jedis 连接池配置</p>
	 *<p>param @return</p>
	 *<p>return JedisPool</p>
	 *<p>Company:南天信息工程</p>
	 * @author Acmen
	 * @date 2018年2月28日 上午9:38:07
	 *
	 */
	@Bean
	public JedisPool jedisPool() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMinIdle(minIdle);
		jedisPoolConfig.setMaxWaitMillis(maxWait);
		jedisPoolConfig.setMaxTotal(maxActive);
		log.info("jedis 注入成功"+host+ port);
		JedisPool jedisPool = new JedisPool(jedisPoolConfig, host,port,timeout,password,database);
		return jedisPool;
	}
	/**
	 * 
	 *<p>Title:cacheManager</p>
	 *<p>Description:redis缓存管理器</p>
	 *<p>param @param redisTemplate
	 *<p>param @return</p>
	 *<p>return CacheManager</p>
	 *<p>Company:南天信息工程</p>
	 * @author Acmen
	 * @date 2018年2月28日 上午9:57:32
	 *
	 */
	@Bean
	public  CacheManager cacheManager(RedisTemplate redisTemplate) {
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		cacheManager.setDefaultExpiration(3600);//缓存过期时间为1小时
		return cacheManager;
	}
	/**
	 * 自定义生成key主键策略
	 */
	@Bean
	public KeyGenerator keyGenerator() {
		
		return new KeyGenerator() {
			
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuffer sb = new StringBuffer();
				sb.append(target.getClass().getName());//追加类名
				sb.append(method.getName());//追加方法名
				for(Object param : params) {
					sb.append(param.toString());
				}
				return sb;
			}
		};
	}
	/**
	 * 
	 *<p>Title:redisTemplate</p>
	 *<p>Description:redis序列化</p>
	 *<p>param @param redisConnectionFactory
	 *<p>param @return</p>
	 *<p>return RedisTemplate<?,?></p>
	 *<p>Company:南天信息工程</p>
	 * @author Acmen
	 * @date 2018年3月1日 上午10:20:56
	 *
	 */
	@Bean("redisTemplate")
	public RedisTemplate<String,String> redisTemplate(RedisConnectionFactory redisConnectionFactory){
		RedisTemplate<String,String> template = new StringRedisTemplate(redisConnectionFactory);
		//使用jackson 进行序列化和反序列化
		Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer<>(Object.class);
		//对从数据库查询出来的通过mapper文件映射生产序列化
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
		mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		serializer.setObjectMapper(mapper);
		template.setKeySerializer(serializer);
		template.setValueSerializer(serializer);
		template.afterPropertiesSet();
		return template;
	}
}
