package cn.com.zuul.dynamicFilter.redis;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 *<p>Title:RedisAopCache</p>
 *<p>Description:该注解形式作用于Aop切面用于redis缓存</p>
 *<p>Company:南天信息工程</p>
 * @author Acmen
 * @date 2018年2月28日 下午5:20:30
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RedisAopCache {
	String value() default "";
	String key();
}
