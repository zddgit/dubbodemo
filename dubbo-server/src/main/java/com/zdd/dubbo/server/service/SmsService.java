package com.zdd.dubbo.server.service;

import com.zdd.dubbo.api.ISmsService;
import org.apache.dubbo.config.annotation.Service;


@Service
public class SmsService implements ISmsService {
    @Override
    public String sendSms(String mobile, String content, String platform) {
        return String.format("server:%s, %s, %s",mobile,content,platform);
    }
}
