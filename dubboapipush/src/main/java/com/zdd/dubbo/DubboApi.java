package com.zdd.dubbo;

import com.zdd.dubbo.api.ISmsService;
import org.apache.dubbo.config.*;

public class DubboApi {
    public static void main(String[] args) {



    }

    public void initServer(){
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("dubbo-server-raw-api");

        //注册中心配置
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress("127.0.0.1:2181");

        //服务提供者协议配置
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(21880);
        protocolConfig.setThreads(100);

        //服务暴露配置
        ServiceConfig<ISmsService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setInterface(ISmsService.class);
        serviceConfig.setRef(new SmsService());
        //暴露以及注册服务
        serviceConfig.export();

    }

    public  void initClient(){
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("dubbo-client-raw-api");

        //注册中心配置
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress("127.0.0.1:2181");

        //服务提供者协议配置
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(21880);
        protocolConfig.setThreads(100);

        //引用远程服务
        ReferenceConfig<ISmsService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setInterface(ISmsService.class);

        ISmsService iSmsService = referenceConfig.get(); //此处代理对象比较重，请缓存复用
        String result = iSmsService.sendSms("15136427520","啊哈","0.3");
        referenceConfig.destroy();
        System.out.println(result);
    }
}
