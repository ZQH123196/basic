package com.example.nacosspringcloud.discovery;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DiscoveryApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryApplication.class, args);
    }



    @Override
    public void run(ApplicationArguments args) throws Exception {

    }



}
