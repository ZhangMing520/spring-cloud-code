package com.example.nacosconsumersentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.security.DenyAll;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class NacosConsumerSentinelApplication {

	public static void main(String[] args) {
		SpringApplication.run(NacosConsumerSentinelApplication.class, args);
	}

}
