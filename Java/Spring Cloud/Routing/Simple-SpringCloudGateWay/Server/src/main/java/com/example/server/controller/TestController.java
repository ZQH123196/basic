package com.example.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testServer")
public class TestController {

    @RequestMapping("hello")
    public String hello() {
        return "hello world!";
    }
}
