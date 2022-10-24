package com.example.authenticationformlogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    // 运行自定义鉴权函数，beanName.method
    @PreAuthorize("@ps.authorization('system:user:page')")
    @RequestMapping("/helloButAuth")
    public String helloButAuth() {
        return "hello world! VIP User！";
    }

    @PreAuthorize("@hasRole('ADMIN')")
    @RequestMapping("/helloButAdmin")
    public String helloButAdmin() {
        return "hello world! VIP User！";
    }

    // filter 为数组服务，行为类似于 map。
    // 相当于函数式中的 map 函数，value eval 为 true 则加入参数列表
    @PreFilter(filterTarget = "usernameList", value = "filterObject.equals('admin')")
    @RequestMapping("/helloButPreFilter")
    public String helloButPreFilter(@RequestParam(value = "usernameList") List<String> usernameList) {
        // 只会显示 admin，其他的都被过滤掉了
        return "hello world!"+usernameList;
    }

    // 后处理可以做屏蔽的功能，因为返回值只能有一个，所以 filterTarget 不能指定，filterObject 一定等于返回值
    // 这里将返回的文本全部大写，做屏蔽功能的话，只要在返回的类中加上屏蔽的方法并在这里调用即可。
    @PostFilter("filterObject.toUpperCase()")
    @RequestMapping("/helloButPostFilter")
    public String helloButPostFilter() {
        return "hello world!";
    }

    @RequestMapping("/getMvcInterceptorList")
    public List<String> getMvcInterceptorList(HttpServletRequest request) throws Exception {
        List<HandlerInterceptor> interceptorList = handlerMapping.getHandler(request).getInterceptorList();
        List<String> interceptorStringList = interceptorList.stream().map((interceptor) -> interceptor.getClass().getSimpleName()).collect(Collectors.toList());
        return interceptorStringList;
    }

}
