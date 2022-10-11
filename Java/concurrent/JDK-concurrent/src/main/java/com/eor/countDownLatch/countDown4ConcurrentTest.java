package com.eor.countDownLatch;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.sun.corba.se.spi.orbutil.threadpool.ThreadPool;

import java.util.concurrent.*;

public class countDownConcurrent {

    private static CountDownLatch countDownLatch = new CountDownLatch(6);

    public static void main(String[] args) throws Exception {
        Executor executor = Executors.newFixedThreadPool(12);
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();

        //Common Thread Pool
        ExecutorService pool = new ThreadPoolExecutor(5, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        pool.execute(()-> System.out.println(Thread.currentThread().getName()));
        pool.shutdown();//gracefully shutdown
        for (int i = 1; i < 6; i++) {
            executor.execute(() -> {
                try {
                    countDownLatch.wait();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
    }

}
