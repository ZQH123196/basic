package com.alibaba.nacos;

import com.alibaba.nacos.client.config.common.ConfigConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * nacos 2.2.0
 * 官方源码中：console 包中复制而来的主启动类
 * 设置 System.setProperty 的原因是 application.yml 里面的配置似乎并不是全部都能生效
 */
@EnableScheduling
@ServletComponentScan
@SpringBootApplication
public class ClusterApplication {

    public static void main(String[] args) {
        /**
         *设置单机模式启动
         */
        System.setProperty(ConfigConstants.STANDALONE_MODE, "true");
        /**
         * 是否开启认证
         */
        System.setProperty(ConfigConstants.AUTH_ENABLED, "false");
        SpringApplication.run(ClusterApplication.class, args);
    }
}
