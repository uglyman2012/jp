package com.xy.jp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.xy.jp"})
public class JpApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpApplication.class, args);
    }
}
