package com.example.configclientbus;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

    @Value("${foo}")
    private String foo;

    /**
     * 从配置中心读取的foo变量的值
     *
     * @return
     */
    @GetMapping(value = "/hi")
    public String hi() {
        return foo;
    }

}
