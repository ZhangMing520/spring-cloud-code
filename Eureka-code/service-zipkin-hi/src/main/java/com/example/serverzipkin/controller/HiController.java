package com.example.serverzipkin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HiController.class);

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hi")
    public String callHome() {
        LOGGER.info("calling trace service-zipkin-hi");
        return restTemplate.getForObject("http://localhost:8989/info", String.class);
    }

    @GetMapping("/info")
    public String info() {
        LOGGER.info("calling trace service-zipkin-hi");
        return "i'm service-zipkin-hi";
    }

}
