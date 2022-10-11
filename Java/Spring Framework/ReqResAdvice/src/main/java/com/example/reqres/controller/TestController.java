package com.example.webservlet.interception.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/hellow")
    public String hellow(HttpServletRequest request) throws Exception {
        return "hellow world!";
    }

    @RequestMapping("/echo")
    public String echo(@RequestParam String str) {
        return str;
    }

    @RequestMapping("/getInterceptorList")
    public List<String> getInterceptorList(HttpServletRequest request) throws Exception {
        HandlerExecutionChain handlerExecutionChain = handlerMapping.getHandler(request);
        List<HandlerInterceptor> interceptorList = handlerExecutionChain.getInterceptorList();
        List<String> interceptorStrList = interceptorList.stream().map(interceptor -> interceptor.getClass().getName()).collect(Collectors.toList());
        return interceptorStrList;
    }
}
