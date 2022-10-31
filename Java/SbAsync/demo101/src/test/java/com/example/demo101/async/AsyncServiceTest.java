package com.example.demo101.async;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

/**
 * 注意要 join，否则主线程就先跑完啦
 */
@SpringBootTest
class AsyncServiceTest {


    @Resource
    AsyncService asyncService;

    @Test
    void somethingReturn() {
        int count = 10;
        ArrayList<CompletableFuture> futureList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            futureList.add(asyncService.doSomething("index = " + i));
        }

        CompletableFuture
                .allOf(futureList.toArray(new CompletableFuture[futureList.size()]))
                .join();

    }


    @Test
    void nonReturnMethod() throws InterruptedException {
        int count = 10;
        for (int i = 0; i < count; i++) {
            asyncService.nonReturnMethod("index = " + i);
        }

        Thread.sleep(10000L);
    }
}