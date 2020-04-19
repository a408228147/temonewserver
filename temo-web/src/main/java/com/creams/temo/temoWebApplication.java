package com.creams.temo;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "com.creams.temo")
public class temoWebApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        // 解决服务端tomcat 404问题
        return application.sources(temoWebApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(temoWebApplication.class, args);
    }
}
