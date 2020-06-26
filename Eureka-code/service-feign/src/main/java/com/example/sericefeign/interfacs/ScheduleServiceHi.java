package com.example.sericefeign.interfacs;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * {@link FeignClient } FeignClient（“服务名”）,来指定调用哪个服务
 */
@FeignClient(value = "service-hi")
public interface ScheduleServiceHi {

    /**
     * 在代码中调用 “service-hi” 服务的 “/hi” 接口
     *
     * @param name
     * @return
     */
    @GetMapping("/hi")
    String sayHiFromClientOne(@RequestParam(value = "name") String name);

}
