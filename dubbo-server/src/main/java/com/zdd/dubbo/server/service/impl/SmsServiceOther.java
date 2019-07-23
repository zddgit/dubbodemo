package com.zdd.dubbo.server.service.impl;

import com.zdd.dubbo.server.service.OtherService;
import org.apache.dubbo.config.annotation.Service;

import java.util.Date;


@Service
public class SmsServiceOther implements OtherService {

    @Override
    public String sendSmsOther(String mobile, String content, String platform) throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("调用一次sendSmsOther:"+new Date().getTime());
        return String.format("server:%s, %s, %s",mobile,content,platform);
    }
}
