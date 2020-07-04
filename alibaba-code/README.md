#### nacos 
1. 简介
> nacos 致力于帮助您发现，配置和管理微服务。nacos提供了一组简单易用的特性集，帮助您快速的实现动态服务发现、
>服务配置、服务元数据和流量管理。类似Consul、Eureka，同时又提供了分布式配置中心的功能，支持热加载。
>目前主流的服务注册和发现组件有Consul、Eureka、Etcd等

- 服务发现和服务健康监控
- 动态配置服务，带管理界面，支持丰富的配置维度
- 动态DNS服务
- 服务及其元数据管理
[下载地址](https://github.com/alibaba/nacos/releases)
```bash
# nacos 启动之前需要配置 conf/application.properties，主要是数据配置（mysql）

# 启动命令
sh startup.sh -m standalone

# ubuntu 启动错误
./startup.sh: 78: ./startup.sh: [[: not found
./startup.sh: 88: ./startup.sh: [[: not found
./startup.sh: 90: ./startup.sh: [[: not found
./startup.sh: 96: ./startup.sh: [[: not found
/usr/lib/jvm/java-8-openjdk-amd64/bin/java  -server -Xms2g -Xmx2g -Xmn1g -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=320m -XX:-OmitStackTraceInFastThrow -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/data/soft/nacos/logs/java_heapdump.hprof -XX:-UseLargePages -Djava.ext.dirs=/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/ext:/usr/lib/jvm/java-8-openjdk-amd64/lib/ext:/data/soft/nacos/plugins/cmdb:/data/soft/nacos/plugins/mysql -Xloggc:/data/soft/nacos/logs/nacos_gc.log -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M -Dnacos.home=/data/soft/nacos -jar /data/soft/nacos/target/nacos-server.jar  --spring.config.location=classpath:/,classpath:/config/,file:./,file:./config/,file:/data/soft/nacos/conf/ --logging.config=/data/soft/nacos/conf/nacos-logback.xml
./startup.sh: 116: ./startup.sh: [[: not found
nacos is starting，you can check the /data/nacos/logs/start.out

# 使用以下命令启动
bash -f startup.sh -m standalone
```
[启动之后浏览器访问](http://localhost:8848/nacos)