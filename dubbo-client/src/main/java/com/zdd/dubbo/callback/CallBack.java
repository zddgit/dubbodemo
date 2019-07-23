package com.zdd.dubbo.callback;

import org.springframework.stereotype.Component;

@Component
public class CallBack {

    public void onReturn(Object result,Object[] args){
        System.out.println(String.format("result:%s,params:%s",result.toString(),args.toString()));
    }

    public void onThrow(Throwable ex,Object[] args){
        System.out.println(String.format("result:%s,params:%s",ex.getMessage(),args.toString()));
    }

}
