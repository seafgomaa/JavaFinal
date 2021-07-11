package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@RestController
public class HelloResource {

    @GetMapping("/hello-world")
    public String hello() {
        return "Hello, World!";
    }
}