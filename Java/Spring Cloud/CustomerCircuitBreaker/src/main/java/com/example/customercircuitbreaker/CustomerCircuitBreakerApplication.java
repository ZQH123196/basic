package com.example.customercircuitbreaker;

import com.example.customercircuitbreaker.integration.TestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableAspectJAutoProxy
@Slf4j
public class CustomerCircuitBreakerApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(CustomerCircuitBreakerApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {  }
}
