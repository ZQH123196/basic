package com.example.demo101.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@RestController
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @GetMapping("/open/something")
    public String somethingReturn() {
        int count = 10;
        ArrayList<CompletableFuture> futureList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            futureList.add(asyncService.doSomething("index = " + i));
        }


        CompletableFuture
                .allOf(futureList.toArray(new CompletableFuture[futureList.size()]))
                .join();

        return "success";
    }


    @GetMapping("/nonReturnMethod")
    public String nonReturnMethod() {
        int count = 10;
        for (int i = 0; i < count; i++) {
            asyncService.nonReturnMethod("index = " + i);
        }
        return "success";
    }
}
