package com.example.gateway.conf;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AfterStart implements ApplicationRunner {

    @Resource
    RouteLocator routeLocator;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        routeLocator.getRoutes();
    }
}
