package com.example.clustereurekaserver01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ClusterEurekaServer01Application {

    public static void main(String[] args) {
        SpringApplication.run(ClusterEurekaServer01Application.class, args);
    }

}
