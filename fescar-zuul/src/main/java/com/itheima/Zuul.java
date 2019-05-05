package com.itheima;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
@SpringBootApplication(scanBasePackages = "com.itheima")
@EnableZuulProxy
public class Zuul {
    public static void main(String[] args) {
        SpringApplication.run(Zuul.class,args);
    }
}
