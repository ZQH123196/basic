package com.example.eurekaclient.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.PathParam;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/hellow")
    public String hellow() {
        return "hellow world!";
    }

    @RequestMapping("/echo")
    public String echo(@RequestParam String str) {
        return str;
    }


}
