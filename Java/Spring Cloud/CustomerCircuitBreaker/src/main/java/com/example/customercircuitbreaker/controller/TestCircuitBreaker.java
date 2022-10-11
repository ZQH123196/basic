package com.example.customercircuitbreaker.controller;

import com.example.customercircuitbreaker.integration.TestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestCircuitBreaker {

    @Autowired
    TestController testController;

    /**
     * 先测试网络不通
     * 在测试网络通
     * 通过开关微服务来做
     */
    @RequestMapping("/TestCircuitBreaker")
    public String testCircuitBreaker() {
        String hellow = testController.hellow();
        log.info(hellow);
        return hellow;
    }
}
