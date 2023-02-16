package com.example.webfluxsimple.action.handler;

import com.example.webfluxsimple.action.pojo.HelloVo;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
public class MessageHandle {

    public Mono<HelloVo> hello() {
        return Mono.create(sink -> sink.success(new HelloVo()));
    }

    public Mono<Map<String, HelloVo>> helloMap() {
        HashMap<String, HelloVo> map = new HashMap<String, HelloVo>() {{
            put("str", new HelloVo());
        }};
        return Mono.create(sink -> sink.success(map));
    }

    public Flux<Map.Entry<String, HelloVo>> helloMapList() {
        HashMap<String, HelloVo> map = new HashMap<String, HelloVo>() {{
            put("str", new HelloVo());
        }};
        return Flux.fromIterable(map.entrySet());
    }

    public Flux<Map.Entry<String, HelloVo>> helloMapList2() {
        HashMap<String, HelloVo> map = new HashMap<String, HelloVo>() {{
            put("str", new HelloVo());
        }};
        // entrySet 转可迭代
        return Flux.fromIterable(map.entrySet());
    }
}
