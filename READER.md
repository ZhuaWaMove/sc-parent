Zuul：构建高可用网关之多维度限流
    对请求的目标URL进行限流（例如：某个URL每分钟只允许调用多少次）
    对客户端的访问IP进行限流（例如：某个IP每分钟只允许请求多少次）
    对某些特定用户或者用户组进行限流（例如：非VIP用户限制每分钟只允许调用100次某个API等）
    多维度混合的限流。此时，就需要实现一些限流规则的编排机制。与、或、非等关系。

    spring-cloud-zuul-ratelimit是和zuul整合提供分布式限流策略的扩展，只需在yaml中配置几行配置，就可使应用支持限流
    
    <dependency>
        <groupId>com.marcosbarbero.cloud</groupId>
        <artifactId>spring-cloud-zuul-ratelimit</artifactId>
        <version>1.3.4.RELEASE</version>
    </dependency>
    
支持的限流粒度
    
    服务粒度 (默认配置，当前服务模块的限流控制)
    用户粒度 （详细说明，见文末总结）
    ORIGIN粒度 (用户请求的origin作为粒度控制)
    接口粒度 (请求接口的地址作为粒度控制)
    以上粒度自由组合，又可以支持多种情况。
    如果还不够，自定义RateLimitKeyGenerator实现。

支持的存储方式

    InMemoryRateLimiter - 使用 ConcurrentHashMap作为数据存储
    ConsulRateLimiter - 使用 Consul 作为数据存储
    RedisRateLimiter - 使用 Redis 作为数据存储
    SpringDataRateLimiter - 使用 数据库 作为数据存储

限流配置

    limit 单位时间内允许访问的个数
    quota 单位时间内允许访问的总时间（统计每次请求的时间综合）
    refresh-interval 单位时间设置

    zuul:
      ratelimit:
        key-prefix: your-prefix 
        enabled: true 
        repository: REDIS 
        behind-proxy: true
        policies:
          myServiceId:
            limit: 10
            quota: 20
            refresh-interval: 30
            type:
              - user
            
    以上配置意思是：30秒内允许10个访问，并且要求总请求时间小于20秒
 ############################################################################################################
 另一篇博文：
 
 API限流
 
 微服务开发中有时需要对API做限流保护，防止网络攻击，比如做一个短信验证码API，限制客户端的请求速率能在一定程度上抵御短信轰炸攻击，降低损失。
 
 微服务网关是每个请求的必经入口，非常适合做一些API限流、认证之类的操作，这里有一个基于zuul微服务网关的API限流库：
 https://github.com/marcosbarbero/spring-cloud-zuul-ratelimit
 使用方法
 
 比如我们要对userinfo-consumer这个服务进行限流，限制每个请求源每分钟最多只能请求三次，首先在项目中添加zuul和ratelimit的依赖，然后再添加如下配置即可：
 
     1.zuul.routes.userinfo.path=/getuser/**
     2.zuul.routes.userinfo.serviceId=userinfo-consumer
     3.zuul.ratelimit.enabled=true
     4.zuul.ratelimit.policies.userinfo.limit=3
     5.zuul.ratelimit.policies.userinfo.refresh-interval=60
     6.zuul.ratelimit.policies.userinfo.type=origin
 

 
     测试客户端如果60s内请求超过三次，服务端就抛出异常，一分钟后又可以正常请求
     某个IP的客户端被限流并不影响其他客户端，即API网关对每个客户端限流是相互独立的
 
 原理分析
 
     对API限流是基于zuul过滤器完成的，如果不使用redis，限流数据是记录在内存中的，一般在开发环境中可以直接记录在内存中，生产环境中还是要使用Redis。
 
 限流拦截时机
 
 限流过滤器是在请求被转发之前调用的
 
     @Override
     public String filterType() {
         return "pre";
     }
 
 限流类型
 
 限流类型主要包括url、origin、user三种
 
    if (types.contains(URL)) {
        joiner.add(route.getPath());
    }
    if (types.contains(ORIGIN)) {
        joiner.add(getRemoteAddr(request));
    }
    if (types.contains(USER)) {
        joiner.add(request.getUserPrincipal() != null ? request.getUserPrincipal().getName() : ANONYMOUS);
    }
     1. url 类型的限流就是通过请求路径区分
     2. origin 是通过客户端IP地址区分
     3. user 是通过登录用户名进行区分，也包括匿名用户
     4. 也可以多个限流类型结合使用
     5. 如果不配置限流类型，就不做以上区分
 
 拦截限流请求
 
 在过滤器的run方法中判断请求剩余次数
 
    if (rate.getRemaining() < 0) {
        ctx.setResponseStatusCode(TOO_MANY_REQUESTS.value());
        ctx.put("rateLimitExceeded", "true");
        throw new ZuulRuntimeException(new ZuulException(TOO_MANY_REQUESTS.toString(),
                TOO_MANY_REQUESTS.value(), null));
    }
 
     单位时间剩余请求次数小于0时抛出ZuulRuntimeException，直接返回客户端TOO_MANY_REQUESTS异常，达到拦截请求的效果









