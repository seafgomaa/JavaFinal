package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class HelloResource {

    @GetMapping("/hi")
    public String hello(@RequestParam(defaultValue = "Mohamed") String name) {
        String temp="Hello, ";
        return  temp+name;
    }
}