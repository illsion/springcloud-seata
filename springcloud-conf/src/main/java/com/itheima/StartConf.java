package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer //开启配置服务
@SpringBootApplication
public class StartConf {


    public static void main(String[] args) {
        SpringApplication.run(StartConf.class,args);
    }
}
