package com.example.customercircuitbreaker.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "eureka-client", contextId = "echo", path = "test")
public interface TestController {
    @RequestMapping("/echo")
    public String echo(@RequestParam String str);

    @RequestMapping("/hellow")
    public String hellow();
}
