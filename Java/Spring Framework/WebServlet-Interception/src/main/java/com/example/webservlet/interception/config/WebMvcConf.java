package com.example.webservlet.interception.config;

import com.example.webservlet.interception.interception.LogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConf implements WebMvcConfigurer {

    // https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-config-interceptors
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**");

//        registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/secure/*");
    }
}
