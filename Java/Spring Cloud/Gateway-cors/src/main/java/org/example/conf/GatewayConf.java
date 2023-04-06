package com.example.gateway.conf;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConf {

//    @Bean
//    public RouteLocator myRoutes(RouteLocatorBuilder routeLocatorBuilder) {
//        return routeLocatorBuilder
//                .routes()
//                .route(path -> path
//                        .path("/get")
//                        .filters(f -> f.addRequestHeader("Hello", "World"))
//                        .uri("http://httpbin.org:80"))
//                .build();
//    }
}
