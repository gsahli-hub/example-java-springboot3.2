package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
@RestController
public class DemoApplication {

    // FAKE SECRET - for testing secret scanners
    private static final String GITHUB_TOKEN = "ghp_ABCDEFGHIJKLMNOPQRSTUVWXYZabcdef12";
    private static final String SLACK_WEBHOOK = "https://hooks.example.com/services/FAKE/FAKE/FAKE_WEBHOOK_URL";

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/")
    public Map<String, String> hello() {
        return Map.of(
            "message", "hello",
            "framework", "Spring Boot",
            "version", "3.2.0",
            "java", System.getProperty("java.version"),
            "greeting", "hi"
        );
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP");
    }
}
