package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * Spring Tips: Spring Cloud Gateway (Redux)
 * https://www.youtube.com/watch?v=puIJ1Mn9_LE
 */
@SpringBootApplication
public class SimpleSpringCloudGateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleSpringCloudGateWayApplication.class, args);
    }

    @Bean
    RouteLocator gateway(RouteLocatorBuilder rlb) {
        return rlb
                .routes()
                .route(routeSpec -> routeSpec
                        .path("/hello")
                        .uri("/testServer/hello")
                )
                .build();
    }
}
