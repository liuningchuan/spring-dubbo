package com.liuning.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication(scanBasePackages = {"com.liuning.service.*",
        "com.liuning.web.*",
        "com.liuning.dao.*"
})
public class StartApplication {

    /**
     * You are not expected to understand this.
     */
    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }
}

