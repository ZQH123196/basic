package com.example.sbsourcelearning.initialize;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;

/**
 * @author ZQH12
 */
@Order(3)
public class ThirdInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("自定义初始化器：" + this.getClass().getSimpleName());
    }
}
