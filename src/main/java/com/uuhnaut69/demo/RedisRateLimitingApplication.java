package com.uuhnaut69.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisRateLimitingApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisRateLimitingApplication.class, args);
    }

}
