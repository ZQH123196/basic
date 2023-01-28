package com.example.ratelimiter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.PrincipalNameKeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

/**
 * Spring Tips: Spring Cloud Gateway (Redux)
 * https://www.youtube.com/watch?v=puIJ1Mn9_LE
 * <p>
 * 使用 curl -v 来请求，或者开浏览器 network 保持，可以看到其实现是 301 永久重定向
 * 可以使用 actuator
 */
@SpringBootApplication
@Slf4j
public class GateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class, args);
    }

    @Value("http://localhost:9000")
    private String testServerUri;

    @Bean
    RouteLocator gateway(RouteLocatorBuilder rlb) {
        return rlb
                .routes()
                .route(routeSpec -> routeSpec
                        .path("/hello")
                        .filters(fs -> fs.requestRateLimiter(config -> config
                                .setRateLimiter(new RedisRateLimiter(5, 5))
                                .setKeyResolver(new PrincipalNameKeyResolver()))
                        )
                        .uri(testServerUri)
                )
                .build();
    }


}
