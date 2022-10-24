package com.example.demo101.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * 1、@Async注解一般用在类的方法上，如果用在类上，那么这个类所有的方法都是异步执行的；
 * 2、所使用的@Async注解方法的类对象应该是Spring容器管理的bean对象；
 * 3、调用异步方法类上需要配置上注解@EnableAsync。
 *
 * 异步的方法有：
 * 最简单的异步调用，返回值为 void
 * 带参数的异步调用，异步方法可以传入参数
 * 存在返回值，常规调用将返回 Future
 *
 * @Async注解会在以下几个场景失效，也就是说明明使用了@Async注解，但就没有走多线程。
 *
 * 异步方法使用static关键词修饰；
 * 异步类不是一个Spring容器的bean（一般使用注解@Component和@Service，并且能被Spring扫描到）；
 * SpringBoot应用中没有添加@EnableAsync注解；
 * 在同一个类中，一个方法调用另外一个有@Async注解的方法，注解不会生效。原因是@Async注解的方法，是在代理类中执行的。
 *
 * AsyncExecutionInterceptor#invoke 是代理类实际调用的地方，也是 debug 的地方。
 */
@Slf4j
@Service
@Async("poolA")
public class AsyncService {

//    @Async("poolA")
    public CompletableFuture<String> doSomething(String message) {
        log.info("do something, message={}", message);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("do something error: ", e);
        }

        return CompletableFuture.completedFuture(message);
    }

//    @Async("poolA")
    public void nonReturnMethod(String msg) {
        log.info("into nonReturnMethod！msg={}", msg);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("do something error: ", e);
        }
    }
}