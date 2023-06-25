package com.example.java.sm.framework;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class FilterConfig {


    @Bean
    FilterRegistrationBean<? extends Filter> smFilter() {
        FilterRegistrationBean<SmFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SmFilter());
        return registrationBean;
    }
}
