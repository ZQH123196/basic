package com.example.authenticationhttpbasic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/anyoneAllow")
@RestController
public class AnyoneAllowController {

    // http://localhost:8080/anyoneAllow/hello
    @RequestMapping("/hello")
    public String hello() {
        return "hello world!";
    }

}
