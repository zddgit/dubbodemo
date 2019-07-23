package com.zdd.dubbo.api;

public interface ISmsService {

    String sendSms(String mobile, String content, String platform) throws InterruptedException;
}
