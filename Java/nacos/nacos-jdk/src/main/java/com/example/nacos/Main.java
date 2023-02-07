package com.example.nacos;


import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.client.config.NacosConfigService;

import java.util.Properties;
import java.util.concurrent.Executor;

public class Main {
    public static void main(String[] args) throws Exception {
//        com.alibaba.nacos.client.config.NacosConfigService

        String serverAddr = "localhost:8848";
        String dataId = "test";
        String group = "DEFAULT_GROUP";
        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
//        ConfigService configService = NacosFactory.createConfigService(properties);
        ConfigService configService = new NacosConfigService(properties);
        String content = configService.getConfig(dataId, group, 1000 * 10);
        System.out.println("init from remote config server: " + content);
        // 客户端轮询注册中心
        configService.addListener(dataId, group, new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(String configInfo) {
                System.out.println("auto refresh: " + content);
            }
        });

        // 避免主线程挂掉
        System.in.read();
    }
}
