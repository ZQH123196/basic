package com.example.reqres.controller;

import com.example.reqres.controller.vo.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    HandlerMapping handlerMapping;

    @RequestMapping("/hello")
    public String hello(HttpServletRequest request) throws Exception {
        return "hello world!";
    }

    @RequestMapping("/echo")
    public String echo(@RequestParam String str) {
        return str;
    }

    @RequestMapping("/echoInfo")
    public Info echoInfo(@RequestBody Info info) {
        return info;
    }



    @RequestMapping("/getInterceptorList")
    public List<String> getInterceptorList(HttpServletRequest request) throws Exception {
        HandlerExecutionChain handlerExecutionChain = handlerMapping.getHandler(request);
        List<HandlerInterceptor> interceptorList = handlerExecutionChain.getInterceptorList();
        List<String> interceptorStrList = interceptorList.stream().map(interceptor -> interceptor.getClass().getName()).collect(Collectors.toList());
        return interceptorStrList;
    }
}
