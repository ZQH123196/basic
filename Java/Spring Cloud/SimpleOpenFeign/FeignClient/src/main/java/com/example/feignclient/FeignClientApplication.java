package com.example.feignclient;

import com.example.feignclient.remote.EchoServer;
import feign.Feign;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.discovery.simple.SimpleDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignContext;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZQH12
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class FeignClientApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(FeignClientApplication.class, args);
    }


    @Resource
    EchoServer echoServer;

    @Resource
    SimpleDiscoveryClient simpleDiscoveryClient;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<String> serviceNames = simpleDiscoveryClient.getServices();
        System.out.println(serviceNames);
        for (String serviceName : serviceNames) {
            System.out.println("------------");
            System.out.println(simpleDiscoveryClient.getInstances(serviceName));
            System.out.println("------------");
        }

        call();

    }

    private void call() {
        String hello = echoServer.hello();
        String echo = echoServer.echo("openfeign 调用。");

        System.out.println(hello);
        System.out.println(echo);
    }
}
