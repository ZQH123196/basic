package com.eor.log.dynamicMdc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 这是将多线程日志打到一起，使用装饰器模式实现
 * 因为 MDC 里面是 threadlocal 实现，所以新的线程里面的 MDC map 是空的，因此要重新赋值
 * 通过将主线程的 key 传递进去子线程，子线程每次运行前都重新赋值主线程的 key 来实现
 *
 * https://logback.qos.ch/manual/mdc.html#managedThreads
 * https://mp.weixin.qq.com/s/Jpko4eGzRxblEymoq-atdA
 *
 * 一个更好的做法是重写线程池 https://blog.csdn.net/uuqaz/article/details/124404806
 */
public class TestMdc {

    private static final String KEY = "requestId";
    private static final Logger logger = LoggerFactory.getLogger(TestMdc.class);
    private static final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws InterruptedException {

        // 入口传入请求ID
        MDC.put(KEY, UUID.randomUUID().toString());

        // 主线程打印日志
        logger.debug("log in main thread");

        // 异步线程打印日志，用MDCRunnable装饰Runnable
        Thread thread = new Thread(new MDCRunnable(new Runnable() {
            @Override
            public void run() {
                logger.debug("log in other thread");
            }
        }));
        thread.start();

        // 异步线程池打印日志，用MDCRunnable装饰Runnable
        EXECUTOR.execute(new MDCRunnable(new Runnable() {
            @Override
            public void run() {
                logger.debug("log in other thread pool");
            }
        }));
        thread.join();
        EXECUTOR.shutdown();

        // 出口移除请求ID
        MDC.remove(KEY);

    }
}
