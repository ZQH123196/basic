package com.example.mvcinterception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;

@SpringBootApplication
public class MvcInterceptionApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(MvcInterceptionApplication.class, args);
    }

    @Autowired
    ApplicationContext applicationContext;




    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
