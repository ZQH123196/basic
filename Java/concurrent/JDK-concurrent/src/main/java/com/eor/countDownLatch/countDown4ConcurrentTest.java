package com.eor.countDownLatch;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.concurrent.*;

/**
 * 用于简单的压力测试
 * 可以作为发号枪来使用
 */
public class countDown4ConcurrentTest {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();

        //Common Thread Pool
        ExecutorService pool = new ThreadPoolExecutor(5, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        for (int i = 1; i < 6; i++) {
            pool.execute(() -> {

                try {
                    countDownLatch.await();
                    // dosome business
                    LocalDateTime localDateTime = LocalDateTime.now();
                    System.out.println(Thread.currentThread().getName()
                            + " : " +
                            localDateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }
        countDownLatch.countDown();
        // 等待那些子线程完成
        Thread.sleep(4000);
        pool.shutdown();//gracefully shutdown
    }

}
