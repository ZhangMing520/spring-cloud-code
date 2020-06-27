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

####  hystrix
1. 断路器
> 在微服务架构中，根据业务拆分成一个个服务，服务与服务之间可以相互调用（rpc），在spring cloud 中可以通过 ribbon+restTemplate 和 feign 调用。
>为了保证高可用，单个服务通常会集群不熟。由于网络或者自身原因，服务并不能保证100%可用，如果单个服务出现问题，调用这个服务就会出现线程阻塞，
>此时如果有大量的请求涌入，servlet容器的线程资源会被消耗完毕，导致服务瘫痪。服务与服务之前的依赖性，故障会传播，会对整个微服务系统造成灾难性的后果，
>这就是服务器的“雪崩”效应

> 当对特定的服务的调用的不可用达到一个阀值（Hystrix 是5秒20次） 断路器将会被打开

![断路](service-hystrix/service-hystrix.png)

2. ribbon 和 feign 熔断器
- ribbon参见（service-hystrix）  feign 参见（service-feign）
- feign是自带断路器的，在D版本的Spring Cloud之后，它没有默认打开。需要在配置文件中配置打开它 feign.hystrix.enabled=true
- ribbon指定断路方法；feign指定断路类，类实现被@FeignClient注解的接口

#### zuul
1. zuul 简介
> zuul的主要功能是路由转发与过滤。路由功能是微服务的一部分，比如/api/user转发到user服务，/api/shop转发到shop服务。
>zuul默认和Ribbon结合实现了负载均衡的功能。

2. 微服务治理组件
> 服务注册与发现，服务消费，负载均衡，断路器，智能路由，配置管理等等。客户端请求首先经过负载均衡（zuul，Ngnix）,再到服务网关（zuul集群），
>然后再到具体的服务。服务统一注册到高可用的服务注册中心集群，服务的所有的配置文件由配置服务管理，配置服务的配置文件放在git仓库
![简单架构图](service-zuul/简单微服务架构.png)

3. zuul包含功能
- Authentication
- Insights
- Stress Testing 
- Canary Testing 
- Dynamic Routing
- Service Migration
- Load Shedding 
- Security
- Static Response Handling
- Inactive/Active Traffic Management

4. 测试zuul
> 打开浏览器访问：http://localhost:8769/api-a/hi?name=forezp ;浏览器显示:hi forezp,i am from port:8762
> 打开浏览器访问：http://localhost:8769/api-b/hi?name=forezp ;浏览器显示：hi forezp,i am from port:8762
> 过滤功能见 MyFilter

#### config server 
1. 简介
> 方便服务配置文件统一管理，实时更新。分布式配置中心组件spring cloud config ，它支持配置服务放在配置服务的内存中（即本地），也支持放在远程Git仓库中

2. http请求地址和资源文件映射
- {application}/{profile}/{label}
- {application}-{profile}.yml
- /{label}/{application}-{profile}.yml
- /{application}-{profile}.properties
- /{label}/{application}-{profile}.properties

3. config server 测试
> 远程仓库中有个文件config-client-dev.properties文件中有一个属性：foo = foo version 3.
> 启动程序：访问 http://localhost:8888/config-client/dev/master
```json
{
    "name":"config-client",
    "profiles":[
        "dev"],
    "label":"master",
    "version":"4988d99ed29b906cae6c7fb7ba7a02a41fe4d13e",
    "state":null,
    "propertySources":[
        {
            "name":"https://github.com/ZhangMing520/spring-cloud-code//respo/config-client-dev.properties",
            "source":{
                "foo":"foo version 3"
            }
        }]
}
```

5. config client使用config server 
> properties配置之后，直接 @Value 可以注入属性值
![获取属性流程](config-client/获取属性流程.png)

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