package com.example.nacosspringboot;

import com.alibaba.nacos.spring.context.annotation.EnableNacos;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.example.nacosspringboot.service.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class NacosSpringbootApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(NacosSpringbootApplication.class, args);
    }

    @Resource
    UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(userService.test());
        // 测试 refresh
        System.in.read();
        System.out.println(userService.test());
    }
}
