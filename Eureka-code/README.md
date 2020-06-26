
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