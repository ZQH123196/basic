

及其简单，只需要添加依赖
```xml

    <dependencies>
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>
<!--        2020.X.X版本官方重构了bootstrap引导配置的加载方式，需要添加以下依赖：
https://docs.spring.io/spring-cloud-config/docs/current/reference/html/#config-first-bootstrap
-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-bootstrap</artifactId>
        </dependency>
<!--        ...略-->
    </dependencies>
```
然后添加上 @RefreshScope 到需要自动刷新的 bean 和 conroller 上，**注意这里要加到 controller**！
conroller，也一定要加 @RefreshScope；
原因：(123)[https://stackoverflow.com/questions/45137555/refreshscope-not-working-spring-boot]


至于为什么能自动刷新，想象一下也明白 bean 的获取是分很多阶段的，完全可以设置为每次去一次都刷新一边，不过这种性能很低，所以应该是由服务端来主动推送的配置更新。