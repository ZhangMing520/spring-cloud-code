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