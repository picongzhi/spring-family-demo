package com.pcz.spring.family.jpa.complex.repository;

import com.pcz.spring.family.jpa.complex.ApplicationTest;
import com.pcz.spring.family.jpa.complex.model.Coffee;
import com.pcz.spring.family.jpa.complex.model.CoffeeOrder;
import com.pcz.spring.family.jpa.complex.model.OrderState;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
public class CoffeeOrderRepositoryTest extends ApplicationTest {
    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private CoffeeOrderRepository coffeeOrderRepository;

    @Before
    public void init() {
        Coffee latte = Coffee.builder()
                .name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 30.0))
                .build();
        coffeeRepository.save(latte);
        log.info("coffee: {}", latte);

        Coffee espresso = Coffee.builder()
                .name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .build();
        coffeeRepository.save(espresso);
        log.info("coffee: {}", espresso);

        CoffeeOrder order = CoffeeOrder.builder()
                .customer("Pcz")
                .items(Collections.singletonList(espresso))
                .state(OrderState.INIT)
                .build();
        coffeeOrderRepository.save(order);
        log.info("order: {}", order);

        order = CoffeeOrder.builder()
                .customer("Pcz")
                .items(Arrays.asList(espresso, latte))
                .state(OrderState.INIT)
                .build();
        coffeeOrderRepository.save(order);
    }

    @Transactional
    @Test
    public void findTop3ByOrderByUpdateTimeDescIdAscTest() {
        List<CoffeeOrder> coffeeOrders = coffeeOrderRepository.findTop3ByOrderByUpdateTimeDescIdAsc();
        log.info("order size: {}", coffeeOrders.size());
        coffeeOrders.forEach(coffeeOrder -> log.info("order: {}", coffeeOrder));
    }

    @Transactional
    @Test
    public void findByCustomerOrderByIdTest() {
        List<CoffeeOrder> coffeeOrders = coffeeOrderRepository.findByCustomerOrderById("Pcz");
        log.info("order size: {}", coffeeOrders.size());
        coffeeOrders.forEach(coffeeOrder -> log.info("order: {}", coffeeOrder));
    }

    @Transactional
    @Test
    public void findByItemsName() {
        List<CoffeeOrder> coffeeOrders = coffeeOrderRepository.findByItemsName("latte");
        log.info("order size: {}", coffeeOrders.size());
        coffeeOrders.forEach(coffeeOrder -> log.info("order: {}", coffeeOrder));
    }
}
