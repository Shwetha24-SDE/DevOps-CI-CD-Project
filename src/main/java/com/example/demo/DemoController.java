package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/")
    public String home() {
        return "Spring Boot CI/CD Project is Running Successfully 🚀";
    }

    @GetMapping("/health")
    public String health() {
        return "UP";
    }
}
