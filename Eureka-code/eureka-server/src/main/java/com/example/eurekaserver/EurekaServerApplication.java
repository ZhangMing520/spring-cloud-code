package com.example.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 * eureka 是一个高可用组件，没有使用缓存，每个实例注册之后需要向注册中心发送心跳（因此在内存中完成）
 *
 * {@link EnableEurekaServer} 启动一个服务注册中心
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }

}
