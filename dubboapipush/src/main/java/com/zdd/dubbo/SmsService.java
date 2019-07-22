package com.zdd.dubbo;

import com.zdd.dubbo.api.ISmsService;


public class SmsService implements ISmsService {
    @Override
    public String sendSms(String mobile, String content, String platform) {
        return String.format("server:%s, %s, %s",mobile,content,platform);
    }
}
