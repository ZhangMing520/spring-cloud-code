package com.example.sericefeign.controller;

import com.example.sericefeign.interfacs.ScheduleServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

    @Autowired
    private ScheduleServiceHi scheduleServiceHi;

    @GetMapping("/hi")
    public String sayHi(@RequestParam(value = "name", defaultValue = "aaa") String name) {
        return scheduleServiceHi.sayHiFromClientOne(name);
    }
}
