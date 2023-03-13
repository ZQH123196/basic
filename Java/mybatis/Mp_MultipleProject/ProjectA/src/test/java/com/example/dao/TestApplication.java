package com.example.dao;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder().main(TestApplication.class).build().run(args);
    }

}
