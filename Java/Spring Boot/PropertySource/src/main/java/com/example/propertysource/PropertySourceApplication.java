package com.example.propertysource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.JdbcProperties;
import org.springframework.boot.env.RandomValuePropertySource;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

/**
 * PropertySource 自定义样例可以看 RandomValuePropertySource
 * 别忘了加入 spring.factories
 * @author ZQH12
 * @ConfigurationProperties 样例可以看 JdbcProperties
 */
@SpringBootApplication
@Slf4j
public class PropertySourceApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(PropertySourceApplication.class);
        ConfigurableApplicationContext context = app.run(args);

    }

    // 配置文件别忘了加入 spring.factories
    @Value("${demo.name}")
    private String name;

    @Value("${encrypt.base64}")
    private String decodeBase64;


    @Override
    public void run(ApplicationArguments args) throws Exception {
//        log.info("name = [{}]", name);
//        log.info("decodeBase64 = [{}]", decodeBase64);

        System.out.println(decodeBase64);
    }
}
