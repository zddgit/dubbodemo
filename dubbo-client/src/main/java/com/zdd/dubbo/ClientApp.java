package com.zdd.dubbo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class ClientApp {
    public static void main(String[] args) {
        SpringApplication.run(ClientApp.class,args);
    }
}
