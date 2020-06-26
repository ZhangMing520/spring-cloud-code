package com.example.sericefeign.interfacs;

import org.springframework.stereotype.Component;

@Component
public class ScheduleServiceHiHystrix implements ScheduleServiceHi {

    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry " + name;
    }
}
