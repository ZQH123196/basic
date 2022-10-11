package com.example.authenticationhttpbasic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public String hello() {
        return "hello world!";
    }



    @RequestMapping("/getMvcInterceptorList")
    public List<String> getMvcInterceptorList(HttpServletRequest request) throws Exception {
        List<HandlerInterceptor> interceptorList = handlerMapping.getHandler(request).getInterceptorList();
        List<String> interceptorStringList = interceptorList.stream().map((interceptor) -> interceptor.getClass().getSimpleName()).collect(Collectors.toList());
        return interceptorStringList;
    }

}
