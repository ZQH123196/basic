package com.example.propertysource.environment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.PropertiesPropertySourceLoader;

import org.springframework.boot.origin.Origin;
import org.springframework.boot.origin.OriginTrackedValue;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.Base64Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;


public class EncryptTestPropertySource implements EnvironmentPostProcessor {
    private static PropertiesPropertySourceLoader loader = new PropertiesPropertySourceLoader();

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        encryptProperty(environment, application);
    }

    private static void encryptProperty(ConfigurableEnvironment environment, SpringApplication application) {
        MutablePropertySources mutablePropertySources = environment.getPropertySources();
        Resource resource = new ClassPathResource("encrypt.properties");
        try {
            // 这个返回的是不可变的，不能替换
//            propertySource = loader.load(EncryptTestPropertySource.class.getSimpleName(), resource).get(0);
            InputStream inputStream = new FileInputStream(resource.getFile());
            Properties properties = new Properties();
            properties.load(inputStream);
            PropertySource propertySource = new PropertiesPropertySource("encrypt", properties);


            String encodeTargetKey = "encrypt.base64";
            String base64Property = (String)propertySource.getProperty(encodeTargetKey);
            byte[] decode = Base64Utils.decodeFromString(base64Property);
            String decodeBase64Str = new String(decode, "UTF-8");
            Map<String, Object> map = (Map<String, Object>) propertySource.getSource();
            // 因为是浅拷贝，所以修改了这里 propertySource 里的值也就改了
            map.put(encodeTargetKey, decodeBase64Str);
            // 放到最后，即可覆盖默认配置
            mutablePropertySources.addLast(propertySource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
