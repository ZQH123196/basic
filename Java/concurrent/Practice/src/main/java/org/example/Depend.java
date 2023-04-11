package org.example;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
public class Depend {

    /**
     * 体现线程依赖性
     * c 依赖 b 依赖 a
     * 各自取依赖的结果来执行
     * CompletionStage 接口里面描述串行关系，主要是 thenApply、thenAccept、thenRun 和 thenCompose 这四个系列的接口。
     */
    @Test
    public void abc_pass() throws ExecutionException, InterruptedException {
        Supplier<String> startTime = () -> String.valueOf(System.currentTimeMillis())+":";
        Function<? super String, ? extends String> a = pre -> pre+"a";
        Function<? super String, ? extends String> b = pre -> pre+"b";
        Function<? super String, ? extends String> c = pre -> pre+"c";
        CompletableFuture<String> aFuture = CompletableFuture.supplyAsync(startTime);
        CompletableFuture<? extends String> future = aFuture.thenApply(a).thenApply(b).thenApply(c);
        System.out.println(future.get());
        Thread.sleep(TimeUnit.SECONDS.toSeconds(1));

        // 这一句的打印，体现了已经运行的异步任务结果会被复用，或者说 futurestage 可以保存状态。
        System.out.println(future.thenApply(String::toUpperCase).thenApply(pre -> pre+startTime.get()).get());
    }


    /** 禁止使用
     * 通过单线程来实现串行，不过我认为这种应该被禁止使用
     * newSingleThreadExecutor
     */
    @Test
    public void abc_() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> a = executorService.submit(() -> "a");
        Future<String> b = executorService.submit(() -> "b");
        Future<String> c = executorService.submit(() -> "c");
        executorService.awaitTermination(5, TimeUnit.SECONDS);
    }




}