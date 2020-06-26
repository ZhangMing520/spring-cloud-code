#### ribbon 
1. spring cloud 两种服务调用方式
- ribbon + restTemplate
- feign

2. ribbon是一个负载均衡客户端，可以很好的控制htt和tcp的一些行为,ribbon 已经默认实现了这些配置bean
- IClientConfig ribbonClientConfig: DefaultClientConfigImpl
- IRule ribbonRule: ZoneAvoidanceRule
- IPing ribbonPing: NoOpPing
- ServerList ribbonServerList: ConfigurationBasedServerList
- ServerListFilter ribbonServerListFilter: ZonePreferenceServerListFilter
- ILoadBalancer ribbonLoadBalancer: ZoneAwareLoadBalancer

3. @LoadBalanced注解表明这个restTemplate开启负载均衡的功能,在浏览器上多次访问http://localhost:8674/hi?name=forezp，
浏览器交替显示：hi forezp,i am from port:8762 / hi forezp,i am from port:8763 （service-hi 启动多个实例）
![服务架构图](service-ribbon/service-arch.png)

#### feign 
1. Feign简介
> feign 是一个声明式的伪 http 客户端，它使得写 http 客户端变得简单，使用 feign，只需要创建一个接口并注解。它具有可插拔的注解特性，
>可以使用 feign 注解和 JAX-RS 注解。feign 支持可插拔编码器和解码器。feign 默认集成了 ribbon,并和 eureka 结合，默认实现了负载均衡效果

- Feign 采用的是基于接口的注解
- Feign 整合了ribbon，具有负载均衡的能力
- 整合了Hystrix，具有熔断的能力

2. 测试功能
> 在浏览器上多次访问http://localhost:8674/hi?name=forezp， 
> 浏览器交替显示：hi forezp,i am from port:8762 / hi forezp,i am from port:8763 （service-hi 启动多个实例）

#### 注意
1. 错误: 找不到或无法加载主类 com.example.eurekaclient.EurekaClientApplication
```sh 
Maven > Lifecycle > clean
Maven > Lifecycle > compile
```

2. eureka client:Cannot execute request on any known server
- 认真核对 server 地址和端口，一定要对上 
- default-zone 修改为 defaultZone

3. 配置说明
- fetch-registry=false  register-with-eureka=false 表明自己是一个 server
- eureka.client.service-url 服务端设置服务地址，客户端获取服务的地址