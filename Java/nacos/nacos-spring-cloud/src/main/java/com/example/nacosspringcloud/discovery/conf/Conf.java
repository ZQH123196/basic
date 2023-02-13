package com.example.nacosspringcloud.discovery.conf;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.loadbalancer.NacosLoadBalancer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Conf {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }


    /**
     * springcloud 2020.0.x版本移除ribbon，所以不能使用 IRule 类来自定义，但是有更好的
     * LoadBnlancer除了默认的轮询策略外还提供了随机策略RandomLoadBalancer，
     * 如果你使用了nacos的注册发现功能的话，
     * 还可以使用spring-cloud-starter-alibaba-nacos-discovery包里的NacosLoadBalancer策略。
     * https://docs.spring.io/spring-cloud-commons/docs/current/reference/html/#switching-between-the-load-balancing-algorithms
     *
     * @return
     */
//    @Bean
//    public ReactorLoadBalancer<ServiceInstance> loadBalancer(Environment environment,
//                                                             LoadBalancerClientFactory loadBalancerClientFactory,
//                                                             NacosDiscoveryProperties nacosDiscoveryProperties) {
//        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
//
//        return new NacosLoadBalancer(
//                loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class),
//                name,
//                nacosDiscoveryProperties);
//    }
}
