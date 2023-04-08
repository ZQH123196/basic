package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mockHelloOnAnotherServer")
public class HelloController {
    @GetMapping
    @PostMapping
    public String Hello() {
        return "Hello World!";
    }
}
