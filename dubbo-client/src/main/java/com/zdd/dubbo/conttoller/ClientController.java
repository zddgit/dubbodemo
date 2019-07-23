package com.zdd.dubbo.conttoller;

import com.zdd.dubbo.api.ISmsService;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class ClientController {

    //    @Reference(url = "dubbo://127.0.0.1:20880") //直接写这个，就不需要在启动类上写@EnableDubbo
//    @Reference(retries = 2, loadbalance = "random", methods = {@Method(name ="sendSms",onreturn = "callBack.onReturn")})
//    @Reference(retries = 2, loadbalance = "random")
    @Resource
    private ISmsService smsService;

    //可实现并行调用
    @GetMapping("test")
    public String test() throws ExecutionException, InterruptedException {
        String result = smsService.sendSms("15136427520", "hello,dubbo", "openjdk");
        Future<String> stringFuture = RpcContext.getContext().getFuture();
        result = stringFuture.get();
        String result1 = smsService.sendSms("15136427520", "hello,dubbo", "openjdk");
        Future<String> stringFuture1 = RpcContext.getContext().getFuture();
        result1 = stringFuture1.get();
        return result;
    }

    //注解方式实现异步调用暂未调试通过，只能通过xml方式
    @GetMapping("test1")
    public String test1() throws InterruptedException {
       return smsService.sendSms("15136427520", "hello,dubbo1", "openjdk1");
    }


}
