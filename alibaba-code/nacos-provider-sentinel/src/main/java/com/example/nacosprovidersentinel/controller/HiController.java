package com.example.nacosprovidersentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

    /**
     * {@linkplain @SentinelResource} 有以下属性：
     * value  资源名称，不能为空
     * entryType  entry类型，默认为 EntryType.OUT
     * blockHandler / blockHandlerClass   blockHandler 对应处理 BlockException 的函数名称
     * fallback  fallback 函数名称，用于在抛出异常的时候提供 fallback 处理逻辑
     *
     * @param name
     * @return
     */
    @GetMapping("/hi")
    @SentinelResource(value = "hi")
    public String hi(@RequestParam(value = "name", defaultValue = "forezp", required = false) String name) {
        return "hi " + name;
    }
}
