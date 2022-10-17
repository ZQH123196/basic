package com.eor.jpa;



import com.eor.jpa.entity.Coffee;
import com.eor.jpa.entity.CoffeeOrder;
import com.eor.jpa.entity.constant.OrderState;
import com.eor.jpa.repository.CoffeeOrderRepository;

import com.eor.jpa.repository.CoffeeRepository;
import com.eor.jpa.service.CoffeeOrderService;
import com.eor.jpa.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@EnableTransactionManagement
@SpringBootApplication
@EnableJpaRepositories
public class App implements ApplicationRunner {
    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    private CoffeeService coffeeService;
    @Autowired
    private CoffeeOrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("All Coffee: {}", coffeeRepository.findAll());

        Optional<Coffee> latte = coffeeService.findOneCoffee("Latte");
        if (latte.isPresent()) {
            CoffeeOrder order = orderService.createOrder("Li Lei", latte.get());
            log.info("Update INIT to PAID: {}", orderService.updateState(order, OrderState.PAID));
            log.info("Update PAID to INIT: {}", orderService.updateState(order, OrderState.INIT));
        }
    }
}



