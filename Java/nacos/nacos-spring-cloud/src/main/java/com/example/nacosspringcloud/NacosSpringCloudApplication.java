package com.example.nacosspringcloud;

import com.example.nacosspringcloud.autorefresh.AutoRefreshConf;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@SpringBootApplication
public class NacosSpringCloudApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(NacosSpringCloudApplication.class, args);
    }

    @Resource
    AutoRefreshConf autoRefreshConf;

    /**
     * nacos 拿数据会在这个 run 之后，所以不能在这里测试
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
//        Thread.sleep(3*1000);
//        System.out.println(autoRefreshConf.getUsername() + ":" + autoRefreshConf.getPassword());
//        System.in.read();
//        System.out.println(autoRefreshConf.getUsername() + ":" + autoRefreshConf.getPassword());
    }

    @RestController
    class TestController {
        @RequestMapping("/test")
        public String test() {
            return autoRefreshConf.getUsername() + ":" + autoRefreshConf.getPassword();
        }
    }


}
