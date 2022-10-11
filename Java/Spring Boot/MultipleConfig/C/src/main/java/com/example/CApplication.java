package com.example.b;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;



@SpringBootApplication
@Slf4j
public class BApplication implements ApplicationRunner {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(BApplication.class)
                .run(args);
    }

    @Value("${name.A}")
    String nameA;

    @Value("${name.B}")
    String nameB;

    @Value("${name.C}")
    String nameC;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info(nameA);
        log.info(nameB);
        log.info(nameC);
    }
}
