package com.example.webfluxsimple.router.router;

import com.example.webfluxsimple.router.handler.MessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class MessageRouter {

    @Bean
    public RouterFunction<ServerResponse> routeEcho(MessageRouter router) {
        return RouterFunctions
                .route(RequestPredicates.GET("/router/hello")
                                .and(RequestPredicates.accept(MediaType.TEXT_HTML)),
                        MessageHandler::helloHandler);

    }
}
