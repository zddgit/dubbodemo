package com.zdd.dubbo.server.service;

public interface OtherService {
    String sendSmsOther(String mobile, String content, String platform) throws InterruptedException;
}
