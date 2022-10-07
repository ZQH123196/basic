package org.example.controller.conf.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.io.Serializable;


@Data
@Component
@RefreshScope
@ConfigurationProperties(prefix = "global")
public class GlobalConf implements Serializable {


    String name;
    int age;

    @Override
    public String toString() {
        return "GlobalConf{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
