package org.example.argument;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 在实际业务场景中，除了请求体中的数据，我们同样需要请求头中的数据，比如 token ，token 中包含当前登陆用户的信息，每一次 RESTful 请求我们都需要从 header 中获取 token 数据处理实际业务，
 * 这种场景，上文提到的 Converter 以及 @RequestBody 显然不能满足我们的需求，此时我们就要换另一种解决方案 : HandlerMethodArgumentResolver
 *
 * https://mp.weixin.qq.com/s/WZhYJi9gyLx3A9mDBL7LqA
 */
@SpringBootApplication
@Slf4j
public class Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        ConfigurableApplicationContext context = app.run(args);

    }


}
