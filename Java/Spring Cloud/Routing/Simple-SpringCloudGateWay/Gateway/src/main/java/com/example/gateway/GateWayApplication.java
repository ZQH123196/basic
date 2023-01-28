package com.example.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Objects;

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
                /**
                 * 简单匹配实例
                 * 发送到 uri，path 是一个 predicate
                 * testServer 并无 /hello，因此从一个路径发送到另 testServer 服务器时需要修改路径
                 * 只有在 predicate 之后才能使用 filter
                 */
                .route(routeSpec -> routeSpec
                        .path("/hello")
                        .filters(gatewayFilterSpec -> gatewayFilterSpec
                                .setPath("/testServer/hello")
                        )
                        .uri(testServerUri)
                )
                /**
                 * 添加判断，可以从 serverWebExchange 从获取请求的上下文
                 * 这里是要求 head 中必须有 name 这个属性
                 */
                .route(routeSpec -> routeSpec
                        .path("/hello")
                        .and()
                        .asyncPredicate(serverWebExchange -> Mono.just(serverWebExchange.getAttributeOrDefault("name", false)))
                        .filters(gatewayFilterSpec -> gatewayFilterSpec
                                .setPath("/testServer/hello")
                        )
                        .uri(testServerUri)
                )
                // 正则实例，转发给第三方服务
                .route("thirdPartyId", routeSpec -> {
                    return routeSpec
                            .path("/thirdParty/**")
                            .filters(fs -> fs
                                    .rewritePath("/thirdParty/(?<handle>.*)", "/${handle}"))
                            .uri(testServerUri);
                })
                .route(routeSpec -> routeSpec
                        .path("/retry")
                        .filters(fs -> fs
                                .retry(5)
                                // test
                                .circuitBreaker(config -> config
                                        .setFallbackUri("forward:/error")
                                ))
                        .uri(testServerUri)
                )
                /**
                 * 断路器的失败重定向，需要对应的依赖
                 * forward:/error，重定向到自己的 error 路径
                 */
                .route(routeSpec -> routeSpec
                        .path("/testError")
                        .filters(fs -> fs
                                .circuitBreaker(config -> config
                                        .setFallbackUri("forward:/error")
                                ))
                        .uri(testServerUri)
                )
                .route(routeSpec -> routeSpec
                        .path("/error")
                        .filters(fs -> fs.filter((exchange, chain) -> {
                            log.info("error handler for [{}]!", exchange.getRequest().getURI());
                            return chain.filter(exchange);
                        }))
                        .uri(testServerUri)
                )
                .build();
    }
}
