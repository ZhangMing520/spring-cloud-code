package com.example.eurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * 服务注册者
 *
 * 当 client 向 server 注册时候，它会提供一些元数据，例如主机端口，URL，主页等。
 *
 * server 从每一个client实例接收心跳消息。如果心跳超时，则通常将该实例从server删除
 */
@SpringBootApplication
@EnableEurekaClient
public class EurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }

}
