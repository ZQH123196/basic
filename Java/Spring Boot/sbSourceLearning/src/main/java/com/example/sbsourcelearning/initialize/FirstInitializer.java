package com.example.sbsourcelearning.initialize;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZQH12
 */
@Order(1)
public class FirstInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        environment.setRequiredProperties("requiredProperties");
        Map<String, Object> map = new HashMap<>();
        map.put("key1", "value1");
        MapPropertySource mapPropertySource = new MapPropertySource("prop", map);
        environment.getPropertySources().addLast(mapPropertySource);
        System.out.println("自定义初始化器：" + this.getClass().getSimpleName());
        System.out.println("获取 maven 属性：" + environment.getProperty("mysetting.maven.buildStamp"));
    }
}
