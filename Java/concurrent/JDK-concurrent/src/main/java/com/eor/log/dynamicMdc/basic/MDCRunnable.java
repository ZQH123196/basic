package com.eor.log.dynamicMdc.basic;

import org.slf4j.MDC;

import java.util.Map;

/**
 * 每次启动线程都创建一个线程专属的值
 *
 * MDC 之所以在异步线程中不生效是因为底层采用 ThreadLocal 作为数据结构，我们调用 MDC.put()方法传入的请求 ID 只在当前线程有效。
 */
public class MDCRunnable implements Runnable {

    private final Runnable runnable;

    private final Map<String, String> map;

    public MDCRunnable(Runnable runnable) {
        this.runnable = runnable;
        // 保存当前线程的MDC值
        this.map = MDC.getCopyOfContextMap();
    }

    @Override
    public void run() {
        // 传入已保存的MDC值
        for (Map.Entry<String, String> entry : map.entrySet()) {
            MDC.put(entry.getKey(), entry.getValue());
        }
        // 装饰器模式，执行run方法
        runnable.run();
        // 移除已保存的MDC值
        for (Map.Entry<String, String> entry : map.entrySet()) {
            MDC.remove(entry.getKey());
        }
    }

}