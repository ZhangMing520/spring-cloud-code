package com.example.nacosconsumer.controller;

import com.example.nacosconsumer.feign.ProviderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * ribbon restTemplate 负载均衡
 */
@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hi-ribbon")
    public String hiRibbon() {
        return restTemplate.getForObject("http://nacos-provider/hi?name=ribbon", String.class);
    }

    @Autowired
    private ProviderClient providerClient;

    @GetMapping("/hi-feign")
    public String hiFeign() {
        return providerClient.hi("feign");
    }

}
