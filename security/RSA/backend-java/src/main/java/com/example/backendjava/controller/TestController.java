package com.example.backendjava.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {

    @PostMapping("echo")
    public List<String> echo(@RequestBody List<String> stringList) {
        return stringList;
    }
}
