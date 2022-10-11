package com.example.customercircuitbreakr;

import com.example.customercircuitbreakr.integration.TestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@Slf4j
public class CustomerCircuitBreakrApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(CustomerCircuitBreakrApplication.class, args);
    }

    @Autowired
    TestController testController;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String hellow = testController.hellow();
        log.info(hellow);
        String echoStr = testController.echo("sent2echoServer.");
        log.info(echoStr);
    }
}
