package org.example.convert.conf;

import org.example.convert.databinding.String2LocalDateTimeConvert;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConf implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new String2LocalDateTimeConvert());
        WebMvcConfigurer.super.addFormatters(registry);
    }
}
