package com.example.rootservletmanager.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

@RestController
@Slf4j
public class EchoServer {

    @RequestMapping("hello")
    public String hello() throws IOException {
        return "hello";
    }
}
