package com.example.servicehystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * {@link HystrixCommand}注解。该注解对该方法创建了熔断器的功能，并指定了fallbackMethod熔断方法
     *
     * @param name
     * @return
     */
    @HystrixCommand(fallbackMethod = "hiError")
    public String hi(String name) {
        return restTemplate.getForObject("http://SERVICE-HI/hi?name=" + name, String.class);
    }


    public String hiError(String name) {
        return "hi," + name + ",sorry,error!";
    }
}
