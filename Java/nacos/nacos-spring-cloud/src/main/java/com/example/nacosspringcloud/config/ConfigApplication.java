package com.example.nacosspringcloud.config;

import com.example.nacosspringcloud.config.autorefresh.AutoRefreshConf;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@SpringBootApplication
@EnableConfigurationProperties
public class ConfigApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
    }

    @Resource
    AutoRefreshConf autoRefreshConf;


    @Override
    public void run(ApplicationArguments args) throws Exception {
//        Thread.sleep(1*1000);
//        System.out.println(autoRefreshConf.getUsername() + ":" + autoRefreshConf.getPassword());
//        System.in.read();
//        System.out.println(autoRefreshConf.getUsername() + ":" + autoRefreshConf.getPassword());
    }


    @RestController
    class TestController {
        @RequestMapping("/test")
        public String test() {
            return autoRefreshConf.getUsername() + " : " + autoRefreshConf.getPassword();
        }
    }


}
