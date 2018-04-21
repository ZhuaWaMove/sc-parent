Spring Cloud Feign 提供声明式服务绑定功能实现对服务接口调用。
依赖
  |- <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-feign</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
     <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-eureka</artifactId>
    </dependency>
    
主类上添加注解开启feign功能 @EnableFeignClient
 Feign实现的消费者利用Ribbon 维护了针对 注解内@FeignClient(value = "hello-service" )的服务的列表信息，并且通过轮询实现了客户端负载均衡。与Ribbon不同的是Feign只需要定义绑定服务的接口，以声明式的方式，简单而优雅的实现了对服务的调用。
 
 参数绑定
   |-注意@RequestHeader("name") String name, @RequestHeader("age") String age 注解必须有value值
   |-@RequestBody User user 绑定的对象需提供无参构造方法。
   
 继承特性
   |-Feign使用SpringMVC进行绑定接口服务时，和服务提供方的controller类中的方法一样，可以复制。但使用Feign的继承特性可以进一步简化代码。
   只需要在pom.xml文件中引入，接口服务提供方的工程jar的依赖即可。
   