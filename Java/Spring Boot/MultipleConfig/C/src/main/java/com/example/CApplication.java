package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
@Slf4j
public class CApplication implements ApplicationRunner {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(CApplication.class)
                .run(args);
    }



    @Value("${C}")
    String nameC;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info(nameC);
    }
}
