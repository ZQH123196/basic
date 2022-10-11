package com.example.propertysource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.JdbcProperties;
import org.springframework.boot.env.RandomValuePropertySource;

/**
 * PropertySource 自定义样例可以看 RandomValuePropertySource
 * @ConfigurationProperties 样例可以看 JdbcProperties
 */
@SpringBootApplication
public class PropertySourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PropertySourceApplication.class, args);

    }

}
