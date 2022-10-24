package com.eor.reactor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@SpringBootApplication
@Slf4j
public class SimpleReactorDemoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(SimpleReactorDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 打开全局 debug 模式，提供更好的数据流跟踪
        Hooks.onOperatorDebug();
        Flux.range(1, 6)
                // 注意顺序造成的区别
                .doOnRequest(n -> log.info("Request {} number", n))
                .publishOn(Schedulers.boundedElastic())
                .doOnComplete(() -> log.info("Publisher COMPLETE 1"))
                .map(i -> {
                    log.info("Publish {}, {}", Thread.currentThread(), i);
                    // 演示错误处理
                    if (i == 3) {
                        throw new RuntimeException("流水线错误！");
                    }
//					return 10 / (i - 3);
                    return i;
                })
                .doOnComplete(() -> log.info("Publisher COMPLETE 2"))
                // 影响下面的 reactor
				.subscribeOn(Schedulers.single())
				.onErrorResume(e -> {
					log.error("onErrorResume Exception {}", e.toString());
					return Mono.just(-1);
				})
//				.onErrorReturn(-1)
                .subscribe(i -> log.info("Subscribe {}: {}", Thread.currentThread(), i),
                        e -> log.error("error {}", e.toString()),
                        () -> log.info("Subscriber COMPLETE")//,
//						s -> s.request(4)
                );
        Thread.sleep(2000);
    }
}

