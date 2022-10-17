package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author ZQH12
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DiscoveryClient discoveryClient;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        getByRestemplate();
    }

    public void getByRestemplate() {
        // 1、获取某个微服务的可用主机实例，一个微服务后面可能有多台机子，所以是 list
        List<ServiceInstance> instanceList = discoveryClient.getInstances("producer");

        // 2、在这里可以自己做一个负载均衡
        ServiceInstance serviceInstance = instanceList.get(0);

        // 3、从元数据信息获取 host port，并构成 http 调用
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        String url = "http://" + host + ":" + port + "/test/hello";
        System.out.println("===============>>>从EurekaServer集群获取服务实例拼接的url：" + url);


        // httpclient 利用封装好的内容进行远程调用
        String hello = restTemplate.getForObject(url, String.class);
        System.out.println("res:[" + hello + "]");

    }


    /**
     * 服务注册到Eureka之后的改造
     * @param userId
     * @return
     */
    /*@GetMapping("/checkState/{userId}")
    public Integer findResumeOpenState(@PathVariable Long userId) {
        // TODO 从Eureka Server中获取我们关注的那个服务的实例信息以及接口信息
        // 1、从 Eureka Server中获取lagou-service-resume服务的实例信息（使用客户端对象做这件事）
        List<ServiceInstance> instances = discoveryClient.getInstances("lagou-service-resume");
        // 2、如果有多个实例，选择一个使用(负载均衡的过程)
        ServiceInstance serviceInstance = instances.get(0);
        // 3、从元数据信息获取host port
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        String url = "http://" + host + ":" + port + "/resume/openstate/" + userId;
        System.out.println("===============>>>从EurekaServer集群获取服务实例拼接的url：" + url);
        // 调用远程服务—> 简历微服务接口  RestTemplate  -> JdbcTempate
        // httpclient封装好多内容进行远程调用
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        return forObject;
    }*/

}
