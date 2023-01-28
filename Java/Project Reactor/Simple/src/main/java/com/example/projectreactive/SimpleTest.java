package com.example.projectreactive;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

/**
 * remember，nothing happen before subscribe.
 * mono 是 0~1 的数据
 * flux 是 0~max 的数列
 * flux 可以视作为由多个 mono 组成，因此可以互相转换，一个 flux 转为多个 mono，或者多个 mono 转为一个 flux
 */
@Slf4j
public class SimpleTest {

    @Test
    public void testTread1() {
        Flux.range(1, 6)
                .doOnRequest(n -> log.info("Request {} number", n))
                .doOnComplete(() -> log.info("Publisher Complete 1"))
                .publishOn(Schedulers.boundedElastic())
                .map(i -> {
                    log.info("Publisher {} {}", Thread.currentThread(), i);
                    return i;
                })
                .doOnComplete(() -> log.info("Publisher Complete 2"))
                .subscribe(i -> log.info("Subscriber {}: {}", Thread.currentThread(), i),
                        e -> log.error("Subscriber error {}", e.toString()),
                        () -> log.info("Subscriber completed!"));
    }

    /**
     * 添加的线程池，将会直接影响接下来代码的操作
     * publish 是动态线程池
     * subscribe 是单线程
     * 跟上面相比，subscribe 的打印从 main 线程移动到了一个独立线程上
     */
    @Test
    public void testTread2() {
        Flux.range(1, 6)
                .doOnRequest(n -> log.info("Request {} number", n))
                .doOnComplete(() -> log.info("Publisher Complete 1"))
                .publishOn(Schedulers.boundedElastic())
                .map(i -> {
                    log.info("Publisher {} {}", Thread.currentThread(), i);
                    return i;
                })
                .doOnComplete(() -> log.info("Publisher Complete 2"))
                .subscribeOn(Schedulers.single())
                .subscribe(i -> log.info("Subscriber {}: {}", Thread.currentThread(), i),
                        e -> log.error("Subscriber error {}", e.toString()),
                        () -> log.info("Subscriber completed!"));
    }

    @Test
    public void testError() {
        Flux.range(1, 6)
                .doOnRequest(n -> log.info("Request {} number", n))
                .doOnComplete(() -> log.info("Publisher Complete 1"))
                .publishOn(Schedulers.boundedElastic())
                .map(i -> {
                    log.info("Publisher {} {}", Thread.currentThread(), i);
                    // 3 时必定报错
                    return 10 / i - 3;
                })
                .subscribeOn(Schedulers.single())
                .onErrorResume(e -> {
                    log.error("error on onErrorResume!{}", e.toString());
                    return Mono.just(-1);
                })
                .doOnComplete(() -> log.info("Publisher Complete 2"))
                .subscribe(i -> log.info("Subscriber {}: {}", Thread.currentThread(), i),
                        e -> log.error("Subscriber error {}", e.toString()),
                        () -> log.info("Subscriber completed!"));
    }

    @Test
    public void testFlux() {
        List<String> list = new ArrayList<String>() {{
            add("a");
            add("b");
        }};
        Flux<String> stringFlux = Flux.fromIterable(list);
        stringFlux.subscribe(System.out::println);
        stringFlux.map((str) -> str.toUpperCase()).subscribe(System.out::println);
        // flatMap 将返回的所有流进行合并，所以必须返回一个流对象
        stringFlux.flatMap((str) -> Mono.just(str.toUpperCase())).subscribe(System.out::println);
    }

    @Test
    public void testMono() {
        Mono<String> mono = Mono.just("8999");
    }
}
