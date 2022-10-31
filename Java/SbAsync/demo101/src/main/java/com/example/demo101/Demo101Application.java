package com.example.demo101;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//https://mp.weixin.qq.com/s/3EdCHfPbpbmyqd9bA018TA
@SpringBootApplication
public class Demo101Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Demo101Application.class);
//        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

}
