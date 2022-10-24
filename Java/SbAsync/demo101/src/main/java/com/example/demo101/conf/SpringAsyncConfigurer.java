package com.example.demo101.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义线程池有如下模式：
 *
 * 重新实现接口AsyncConfigurer
 * 继承AsyncConfigurerSupport
 * 配置由自定义的TaskExecutor替代内置的任务执行器
 *
 * 注意：只有在缓冲队列满了之后才会申请超过核心线程数的线程
 */
@Configuration
@EnableAsync
@Slf4j
public class SpringAsyncConfigurer extends AsyncConfigurerSupport {

    @Bean("poolA")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        // 核心线程数：线程池创建时候初始化的线程数
        threadPoolTaskExecutor.setCorePoolSize(1);
        // 最大线程数：线程池最大的线程数，**只有在缓冲队列满了之后**才会申请超过核心线程数的线程
        threadPoolTaskExecutor.setMaxPoolSize(10);
        // 缓冲队列：用来缓冲执行任务的队列
        threadPoolTaskExecutor.setQueueCapacity(1);
        // 允许线程的空闲时间60秒：当超过了核心线程之外的线程在空闲时间到达之后会被销毁
        threadPoolTaskExecutor.setKeepAliveSeconds(60);
        // 线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
        threadPoolTaskExecutor.setThreadNamePrefix("A-");
        // 缓冲队列满了之后的拒绝策略：由调用线程处理（一般是主线程）
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        return threadPoolTaskExecutor;
    }

    @Override
    public Executor getAsyncExecutor() {
        return asyncExecutor();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) -> log.error(String.format("执行异步任务'%s'", method), ex);
    }
}
