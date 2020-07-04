package com.example.nacosprovider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

    @GetMapping("/hi")
    public String hi(@RequestParam(value = "name", defaultValue = "forezp", required = false) String name) {
        return "hi " + name;
    }
}
