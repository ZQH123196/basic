package com.example.nacos.discovery;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.Event;
import com.alibaba.nacos.api.naming.listener.EventListener;
import com.alibaba.nacos.api.naming.listener.NamingEvent;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.client.naming.NacosNamingService;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 注册微服务，serviceName 相当于域名，背后有多个 ip 进行负载均衡
 * 注意：一个 naming service 代表一个微服务实例，因此要注册多个实例需要创建多个 name service
 */
public class Main {
    String serviceName = "nacos-jdk";
    String namingServerAddr = "localhost:8848";


    @Test
    public void basic() throws Exception {
        NamingService namingService1 = new NacosNamingService(namingServerAddr);
        namingService1.registerInstance(serviceName, "11.11.11.11", 8999);
        NamingService namingService2 = new NacosNamingService(namingServerAddr);
        namingService2.registerInstance(serviceName, "11.11.11.12", 8999);

        Thread.sleep(1 * 1000);
        NamingService discovery = new NacosNamingService(namingServerAddr);
        for (Instance instance : discovery.getAllInstances(serviceName)) {
            System.out.println(instance);
        }
        System.out.println("-------------------------");
        /**
         * 要注销实例 **必须使用注册时使用的客户端进行注销**。 实例是有状态的数据，和客户端是有关联的。用不同的客户端去注销都无法成功删除。
         * 就好比你服务运行的好好的，有个小天才来给你删了。那你该不该续回来？
         * 你调用接口，他会把实例删除掉， 但是心跳还在，还会恢复的。只能使用注册时的客户端进行注销，心跳才会停止。
         */
        namingService2.deregisterInstance(serviceName, "11.11.11.12", 8999);
        Thread.sleep(2 * 1000);
        for (Instance instance : discovery.getAllInstances(serviceName)) {
            System.out.println(instance);
        }
        System.in.read();
    }

    @Test
    public void cluster() throws Exception {
        NamingService namingService1 = new NacosNamingService(namingServerAddr);
        String clusterGz = "gz";
        String clusterBj = "bj";
        namingService1.registerInstance(serviceName, "11.11.11.11", 8999, clusterGz);
        NamingService namingService2 = new NacosNamingService(namingServerAddr);
        namingService2.registerInstance(serviceName, "11.11.11.12", 8999, clusterBj);
        System.in.read();
    }

    @Test
    public void meta() throws Exception {
        NamingService namingService = new NacosNamingService(namingServerAddr);
        Map<String, String> meta = new HashMap<>();
        meta.put("name", "eor");
        Instance instance = new Instance();
        instance.setIp("11.11.11.11");
        instance.setPort(8999);
        instance.setMetadata(meta);
        namingService.registerInstance(serviceName, instance);

        Thread.sleep(2*1000);
        NamingService discovery = new NacosNamingService(namingServerAddr);
        System.out.println(discovery.selectOneHealthyInstance(serviceName).getMetadata());
        System.in.read();
    }

    /**
     * 订阅微服务实例的变更
     */
    @Test
    public void subscribeServerAlter() throws Exception {
        NamingService discovery = new NacosNamingService(namingServerAddr);
        discovery.subscribe(serviceName, new EventListener() {
            @Override
            public void onEvent(Event event) {
                System.out.println(((NamingEvent) event).getServiceName());
                for (Instance instance : ((NamingEvent) event).getInstances()) {
                    System.out.println(instance);
                }
                System.out.println("-----------------");
            }
        });

        NamingService namingService1 = new NacosNamingService(namingServerAddr);
        namingService1.registerInstance(serviceName, "11.11.11.11", 8999);
        NamingService namingService2 = new NacosNamingService(namingServerAddr);
        namingService2.registerInstance(serviceName, "11.11.11.12", 8999);
        Thread.sleep(2 * 1000);
        namingService1.deregisterInstance(serviceName, "11.11.11.11", 8999);
        System.in.read();
    }

    /**
     * 负载均衡要设置权重
     * setWeight()，默认值就是 1，也就是机会均等
     * 权重根据负载均衡算法有不同效果，但基本可视为权重越大命中概率越高
     */
    @Test
    public void loadBalance() throws Exception {
        NamingService namingServiceHealthy = new NacosNamingService(namingServerAddr);
        Instance instanceHealthy = new Instance();
        instanceHealthy.setWeight(1);
        instanceHealthy.setHealthy(true);
        instanceHealthy.setIp("11.11.11.11");
        instanceHealthy.setPort(8999);
        namingServiceHealthy.registerInstance(serviceName, instanceHealthy);

        NamingService namingServiceHealthy2 = new NacosNamingService(namingServerAddr);
        Instance instanceHealthy2 = new Instance();
        instanceHealthy2.setWeight(1);
        instanceHealthy2.setHealthy(true);
        instanceHealthy2.setIp("11.11.11.12");
        instanceHealthy2.setPort(8999);
        namingServiceHealthy2.registerInstance(serviceName, instanceHealthy2);

        NamingService namingServiceUnhealthy = new NacosNamingService(namingServerAddr);
        Instance instanceUnhealthy = new Instance();
        instanceUnhealthy.setWeight(1);
        instanceUnhealthy.setHealthy(false);
        instanceUnhealthy.setIp("11.11.11.13");
        instanceUnhealthy.setPort(8999);
        namingServiceUnhealthy.registerInstance(serviceName, instanceUnhealthy);

        // 要让注册中心完成自己的注册工作，模拟等待一下
        // 上半部分是注册，下半部分是使用，本来应该是用不同类来实现的
        Thread.sleep(2 * 1000);
        NamingService discoveryService = NamingFactory.createNamingService(namingServerAddr);
        System.out.println("------全部实例 start------");
        for (Instance instance : discoveryService.getAllInstances(serviceName)) {
            System.out.println(instance);
        }
        System.out.println("------全部实例 end------");

        System.out.println("------健康实例 start------");
        for (Instance instance : discoveryService.selectInstances(serviceName, true)) {
            System.out.println(instance);
        }
        System.out.println("------健康实例 end------");

        System.out.println("------负载均衡 start------");

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletionService<?> cs = new ExecutorCompletionService(executorService);
        Runnable runnable = () -> {
            try {
                System.out.println(discoveryService.selectOneHealthyInstance(serviceName));
            } catch (NacosException e) {
                throw new RuntimeException(e);
            }
        };
        for (int i = 0; i < 10; i++) {
            cs.submit(runnable, null);
        }
        for (int i = 0; i < 10; i++) {
            cs.take();
        }
        Thread.sleep(2 * 1000);
        System.out.println("------负载均衡 end------");

        System.in.read();
    }


}
