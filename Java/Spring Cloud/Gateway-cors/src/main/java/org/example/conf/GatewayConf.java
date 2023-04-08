package org.example.conf;

import org.example.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.SetPathGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class GatewayConf {
    @Value("${server.port}")
    int port;

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder routeLocatorBuilder) {
        String uri = "http://localhost:"+port;
        return routeLocatorBuilder
                .routes()
                .route(path -> path
                        .path("/hello")
                        .filters(f -> f.rewritePath("/hello", "/mockHelloOnAnotherServer"))
                        .uri(uri+"/mockHelloOnAnotherServer")
                )
                .build();
    }
}
