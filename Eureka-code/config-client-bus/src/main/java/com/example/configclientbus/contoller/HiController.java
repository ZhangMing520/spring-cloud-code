package com.example.configclientbus.contoller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@link RefreshScope}   需要用在需要刷新的地方（需要获取最新的值等），而不是Application类上
 */
@RestController
@RefreshScope
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
