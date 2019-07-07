package com.zdd.dubbo.server;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class ServerApp {
    public static void main(String[] args) {
        SpringApplication.run(ServerApp.class, args);
    }
}
