package com.example.webfluxsimple.router.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class MessageHandler {

    public static Mono<ServerResponse> helloHandler(ServerRequest request) {
        return ServerResponse
                .ok()
                .header("Content-Type", "text/html;charset=utf-8")
                .body(BodyInserters.fromValue("hello world!"));
    }
}
