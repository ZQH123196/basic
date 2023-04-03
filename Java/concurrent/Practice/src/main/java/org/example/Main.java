package org.example;

import cn.hutool.core.lang.copier.Copier;
import lombok.SneakyThrows;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import sun.misc.JavaObjectInputStreamReadString;

import java.util.Random;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {

    /**
     * 体现线程依赖性
     * c 依赖 b 依赖 a
     * 各自取依赖的结果来执行
     * CompletionStage 接口里面描述串行关系，主要是 thenApply、thenAccept、thenRun 和 thenCompose 这四个系列的接口。
     */
    @Test
    public void abc() throws ExecutionException, InterruptedException {
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


    /**
     * race 竞争，谁先完成用谁的
     */
    @Test
    public void race() {

    }


    /**
     * join 等待，等待所有任务都完成
     */
    @RepeatedTest(5)
    public void join_CompletableFuture() {
        Runnable wasteTime = () -> {
            try {
                int randomSleepSecond = (new Random().nextInt(3)+1) * 1000;
                Thread.sleep(randomSleepSecond); // 随机睡上 1~3s
                System.out.println(Thread.currentThread()+":"+randomSleepSecond);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        CompletableFuture<Void> a = CompletableFuture.runAsync(wasteTime);
        CompletableFuture<Void> b = CompletableFuture.runAsync(wasteTime);
        CompletableFuture<Void> c = CompletableFuture.runAsync(wasteTime);
        CompletableFuture.allOf(a, b, c).join();
    }


    @RepeatedTest(5)
    public void join_() {
        Runnable wasteTime = () -> {
            try {
                int randomSleepSecond = (new Random().nextInt(3)+1) * 1000;
                Thread.sleep(randomSleepSecond); // 随机睡上 1~3s
                System.out.println(Thread.currentThread()+":"+randomSleepSecond);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        Thread thread = new Thread(wasteTime);
    }
}