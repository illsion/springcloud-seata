package com.itheima;

import com.itheima.hystriThread.*;
import com.netflix.hystrix.strategy.HystrixPlugins;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(scanBasePackages = "com.itheima")//包扫描
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
public class StartOrder {

    public static void main(String[] args) {


        List<HystrixCallableWrapper> wrappers = new ArrayList<>();
        wrappers.add(new RequestAttributeAwareCallableWrapper());
        wrappers.add(new MdcAwareCallableWrapper());
//        HystrixPlugins.getInstance().registerConcurrencyStrategy(new RequestContextHystrixConcurrencyStrategy());
        HystrixPlugins.getInstance().registerConcurrencyStrategy(new RequestContextThreadChange(wrappers));
        SpringApplication.run(StartOrder.class,args);
    }
}
