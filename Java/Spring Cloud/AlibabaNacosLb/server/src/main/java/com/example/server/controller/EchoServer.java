package com.example.server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("EchoServer")
public class EchoServer {

    @RequestMapping("hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("echo")
    public String echo(@RequestParam String str) {
        return str;
    }

    @Value("${server.port}")
    String port;

    @RequestMapping("getRemotePort")
    public String getRemotePort() {
        return port;
    }
}
