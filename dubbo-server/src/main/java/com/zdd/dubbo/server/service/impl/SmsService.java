package com.zdd.dubbo.server.service.impl;

import com.zdd.dubbo.api.ISmsService;
import org.apache.dubbo.config.annotation.Service;

import java.util.Date;


@Service
public class SmsService implements ISmsService {
    @Override
    public String sendSms(String mobile, String content, String platform) throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("调用一次"+new Date().getTime());
        return String.format("server:%s, %s, %s",mobile,content,platform);
    }
}
