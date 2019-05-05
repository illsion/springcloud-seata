package com.itheima.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.itheima")
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
public class StartItem {
    public static void main(String[] args) {
        SpringApplication.run(StartItem.class,args);
    }
}
