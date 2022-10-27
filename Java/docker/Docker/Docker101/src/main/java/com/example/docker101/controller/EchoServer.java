package com.example.docker101.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoServer {

    @RequestMapping("hello")
    public String hello() {
        return "hello!";
    }

}
