package com.example.sbsourcelearning;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.env.RandomValuePropertySource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Properties;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SbSourceLearningApplicationTests implements ApplicationContextAware {
    ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
    @Test
    public void test() {
        System.out.println(context.getEnvironment().getProperty("key1"));
        System.out.println(context.getEnvironment().getProperty("requiredProperties"));
    }
}
