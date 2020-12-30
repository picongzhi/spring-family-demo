package com.pcz.spring.family.jpa.repository;

import com.pcz.spring.family.jpa.ApplicationTest;
import com.pcz.spring.family.jpa.model.Coffee;
import com.pcz.spring.family.jpa.model.CoffeeOrder;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collections;

@Slf4j
public class CoffeeOrderRepositoryTest extends ApplicationTest {
    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private CoffeeOrderRepository coffeeOrderRepository;

    @Test
    public void saveTest() {
        Coffee espresso = Coffee.builder()
                .name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .build();
        coffeeRepository.save(espresso);
        log.info("coffee: {}", espresso);

        Coffee latte = Coffee.builder()
                .name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 30.0))
                .build();
        coffeeRepository.save(latte);
        log.info("coffee: {}", latte);

        CoffeeOrder order = CoffeeOrder.builder()
                .customer("Pcz")
                .items(Collections.singletonList(espresso))
                .state(0)
                .build();
        coffeeOrderRepository.save(order);
        log.info("order: {}", order);

        order = CoffeeOrder.builder()
                .customer("Pcz")
                .items(Arrays.asList(espresso, latte))
                .state(0)
                .build();
        coffeeOrderRepository.save(order);
        log.info("order: {}", order);
    }
}
