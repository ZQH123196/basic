package com.eor.springcache;


import com.eor.springcache.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableCaching
@EnableTransactionManagement
@SpringBootApplication
@EnableJpaRepositories
@Slf4j
public class App implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(App.class);
        app.run(args);
    }

    @Autowired
    private CoffeeService coffeeService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 先查一次，findAllCoffee 方法有 @Cacheable 注解
        log.info("Count: {}", coffeeService.findAllCoffee().size());
        // 后续的多次查询都从缓存中取出，因此不会有 sql 查询
        for (int i = 0; i < 10; i++) {
            log.info("Reading from cache.");
            coffeeService.findAllCoffee();
        }
        // reloadCoffee 方法有 @CacheEvict 注解，逐出缓存
        coffeeService.reloadCoffee();
        log.info("Reading after refresh.");
        // 逐出缓存之后，再次查询就会重新拿取缓存了
        coffeeService.findAllCoffee().forEach(c -> log.info("Coffee {}", c.getName()));
    }
}
