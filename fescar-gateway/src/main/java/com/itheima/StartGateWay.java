package com.itheima;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.DispatcherHandler;
import reactor.core.publisher.Mono;
import org.springframework.cloud.gateway.filter.factory.HystrixGatewayFilterFactory;
@SpringBootApplication
@RestController
public class StartGateWay {

    public static void main(String[] args) {
        SpringApplication.run(StartGateWay.class, args);
    }


    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/get")
                        .filters(f -> f.addRequestHeader("Hello", "World"))
                        .uri("http://localhost:8999/item/test"))
                 .route(p -> p
                         .host("*.hystrix.com")
                         .filters(f -> f
                                 .hystrix(config -> config
                                         .setName("mycmd")
                                         .setFallbackUri("forward:/fallback")))
                         .uri("http://localhost:8999/item/test"))
                .build();
    }
    @RequestMapping("/fallback")
    public Mono<String> fallback() {
        System.out.println("fallback被调用了...");
        return Mono.just("fallback");
    }
}
