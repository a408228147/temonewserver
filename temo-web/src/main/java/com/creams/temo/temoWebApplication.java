package com.creams.temo;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.creams.temo")
//@MapperScan("com.creams.temo.mapper")
public class temoWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(temoWebApplication.class, args);
    }
}
