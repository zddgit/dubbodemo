package com.zdd.dubbo.conttoller;

import com.zdd.dubbo.api.ISmsService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

//    @Reference(url = "dubbo://127.0.0.1:20880") //直接写这个，就不需要在启动类上写@EnableDubbo
    @Reference
    private ISmsService smsService;

    @GetMapping("test")
    public String test(){
       return smsService.sendSms("15136427520","hello,dubbo","openjdk");
    }
}
