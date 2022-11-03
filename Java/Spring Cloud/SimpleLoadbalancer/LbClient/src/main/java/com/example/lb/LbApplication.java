package com.example.lb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;

/**
 * @author ZQH12
 */
@SpringBootApplication
public class LbApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(LbApplication.class, args);
    }

    @Resource
    RestTemplate restTemplate;

    @Resource
    LoadBalancerClient loadBalancerClient;

    @Value("EchoServerName")
    String ServerId;

    @Override
    public void run(ApplicationArguments args) throws Exception {


        for (int n = 0; n < 6; n++) {
            // 根据策略每次 choose 的值不同，默认是轮询，因此这里会交替获取
            ServiceInstance serviceInstance = loadBalancerClient.choose(ServerId);
            URI uri = serviceInstance.getUri();
            String hello = restTemplate.getForObject(uri+"/EchoServer/getRemotePort", String.class);
            System.out.println(hello);
        }

    }
}
