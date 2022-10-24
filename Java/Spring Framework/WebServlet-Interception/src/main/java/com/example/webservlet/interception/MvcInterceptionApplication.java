package com.example.webservlet.interception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.servlet.annotation.WebServlet;

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
