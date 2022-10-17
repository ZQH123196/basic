package com.eor.jpa;


import com.eor.jpa.entity.Coffee;
import com.eor.jpa.entity.CoffeeOrder;
import com.eor.jpa.repository.CoffeeOrderRepository;
import com.eor.jpa.repository.CoffeeRepository;
import javafx.scene.canvas.GraphicsContext;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.dialect.H2Dialect;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;

@EnableJpaRepositories

@SpringBootApplication
@Slf4j
public class App implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(App.class);
        ConfigurableApplicationContext context = app.run(args);


    }


    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    private CoffeeOrderRepository orderRepository;
    /**
     * Callback used to run the bean.
     *
     * @param args incoming application arguments
     * @throws Exception on error
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Coffee espresso = Coffee.builder().name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .build();
        coffeeRepository.save(espresso);
        log.info("Coffee: {}", espresso);

        Coffee latte = Coffee.builder().name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 30.0))
                .build();
        coffeeRepository.save(latte);
        log.info("Coffee: {}", latte);

        CoffeeOrder order = CoffeeOrder.builder()
                .customer("Li Lei")
                .items(Collections.singletonList(espresso))
                .state(0)
                .build();
        orderRepository.save(order);
        log.info("Order: {}", order);

        order = CoffeeOrder.builder()
                .customer("Li Lei")
                .items(Arrays.asList(espresso, latte))
                .state(0)
                .build();
        orderRepository.save(order);
        log.info("Order: {}", order);
    }
//    @Bean
//    public CommandLineRunner run(UserRepository userRepository) throws Exception {
//        return (String[] args) -> {
//            User user1 = new User("CommandLineRunner1");
//            User user2 = new User("CommandLineRunner2");
//            userRepository.save(user1);
//            userRepository.save(user2);
////            userRepository.findAll().forEach(user -> System.out.println(user.getName()));
//        };
//    }
}



