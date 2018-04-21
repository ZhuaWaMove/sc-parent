动态刷新配置
    |-只需要在config-client端添加依赖jar
     <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    此jar包中包含/refresh端点，可以实现客户端应用配置信息的重新获取和刷新。
    后期通过Spring Could Bus实现集群中批量配置更新。
获取远程配置信息
    |-给config-server端发送GET请求，请求路径形式如下：
      /{application}-{profile}.yml
      /{application}-{profile}.properties
      /{lable}/{application}-{profile}.yml
      /{lable}/{application}-{profile}.properties
      /{application}/{profile}[/{lable}]
失败快速响应与重试
    |-实现客户端优先判断Config Server获取是否正常，并快速响应失败内容，是需要配置客户端配置参数spring.cloud.config.failFast=true即可。客户端添加重试功能，避免网络波动等因素，需添加jar包，前提是开启了failFast配置。
        <!-- 客户端连接重试-->
       <dependency>
           <groupId>org.springframework.retry</groupId>
           <artifactId>spring-retry</artifactId>
       </dependency>
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-aop</artifactId>
       </dependency>
       通过配置参数可以调整重试次数和时间间隔。
       spring.cloud.config.retry.mutipler:初始重试间隔时间 毫秒默认1000毫秒
       spring.cloud.config.retry.initial-interval:下一间隔的乘数 默认1.1
       spring.cloud.config.retry.max-interval:最大间隔时间 默认*``*2000毫秒
       spring.cloud.config.retry.max-attempts:最大重试次数 默认6次
RabbitMQ的指定刷新范围
      |-
       
