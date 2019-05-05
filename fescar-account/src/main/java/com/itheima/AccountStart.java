package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = "com.itheima")
@EnableEurekaClient
public class AccountStart {
    public static void main(String[] args) {
        SpringApplication.run(AccountStart.class,args);
    }
}
