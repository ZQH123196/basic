package com.example.sbsourcelearning;

import com.example.sbsourcelearning.initialize.SecondInitializer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.sbsourcelearning.mapper")
public class SbSourceLearningApplication {

    public static void main(String[] args) {
//        SpringApplication.run(SbSourceLearningApplication.class, args);
        SpringApplication springApplication = new SpringApplication(SbSourceLearningApplication.class);
        springApplication.addInitializers(new SecondInitializer());
        springApplication.run(args);
    }
}
