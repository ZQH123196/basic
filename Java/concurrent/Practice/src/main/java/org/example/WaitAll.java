package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.Extensions;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.slf4j.MDC;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class WaitAll {
    static Runnable wasteTime;

    @BeforeAll
    public static void beforeAll() {
        wasteTime = () -> {
            try {
                int randomSleepSecond = (new Random().nextInt(3) + 1) * 1000;
                Thread.sleep(randomSleepSecond); // 随机睡上 1~3s
                System.out.println(Thread.currentThread() + ":" + randomSleepSecond);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }

    /**
     * join 等待，等待所有任务都完成
     * <p>
     * 期待结果：每个线程都打印，不会丢失
     */
    @RepeatedTest(5)
    public void join_CompletableFuture() {
        CompletableFuture<Void> a = CompletableFuture.runAsync(wasteTime);
        CompletableFuture<Void> b = CompletableFuture.runAsync(wasteTime);
        CompletableFuture<Void> c = CompletableFuture.runAsync(wasteTime);
        CompletableFuture.allOf(a, b, c).join();
    }

    /**
     * 每个线程都要 join
     * <p>
     * 期待结果：每个线程都打印，不会丢失
     */
    @RepeatedTest(5)
    public void join_Thread() throws InterruptedException {
        Thread thread1 = new Thread(wasteTime);
        Thread thread2 = new Thread(wasteTime);
        Thread thread3 = new Thread(wasteTime);

        thread1.run();
        thread2.run();
        thread3.run();

        thread1.join();
        thread2.join();
        thread3.join();
    }
}
