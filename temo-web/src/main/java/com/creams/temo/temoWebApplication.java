package com.creams.temo;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.creams.temo")

public class temoWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(temoWebApplication.class, args);
    }
}
