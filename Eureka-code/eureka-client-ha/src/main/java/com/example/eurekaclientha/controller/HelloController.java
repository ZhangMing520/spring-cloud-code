package com.example.eurekaclientha.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangming
 * @date 2020/6/26 11:28
 */
@RestController
public class HelloController {

    @Value("${server.port}")
    private String port ;

    @GetMapping("/hi")
    public String home(@RequestParam(value = "name" , defaultValue = "xx")String name ){
        return "hi " + name + " ,i am from port:" + port;
    }
}
