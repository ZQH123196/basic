package com.example.propertysource.environment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;


public class SimpleTestPropertySource implements EnvironmentPostProcessor {
    private static PropertiesPropertySourceLoader loader = new PropertiesPropertySourceLoader();

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        simple(environment, application);
    }

    private static void simple(ConfigurableEnvironment environment, SpringApplication application) {
        MutablePropertySources mutablePropertySources = environment.getPropertySources();
        Resource resource = new ClassPathResource("simple.properties");
        PropertySource propertySource = null;
        try {
            propertySource = loader.load(SimpleTestPropertySource.class.getSimpleName(), resource).get(0);
            // 放到最后，即可覆盖默认配置
            mutablePropertySources.addLast(propertySource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
