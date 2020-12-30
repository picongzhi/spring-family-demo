package com.pcz.spring.bucks.service;

import com.pcz.spring.bucks.ApplicationTest;
import com.pcz.spring.bucks.model.Coffee;
import com.pcz.spring.bucks.model.CoffeeOrder;
import com.pcz.spring.bucks.model.OrderState;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Slf4j
public class CoffeeOrderServiceTest extends ApplicationTest {
    @Autowired
    private CoffeeOrderService coffeeOrderService;

    @Autowired
    private CoffeeService coffeeService;

    @Test
    public void createOrderTest() {
        Optional<Coffee> optional = coffeeService.findOneCoffee("Latte");
        Coffee coffee = optional.get();

        CoffeeOrder coffeeOrder = coffeeOrderService.createOrder("Pcz", coffee);
        log.info("coffeeOrder: {}", coffeeOrder);
    }

    @Test
    public void updateStateTest() {
        Optional<Coffee> optional = coffeeService.findOneCoffee("Latte");
        Coffee coffee = optional.get();

        CoffeeOrder coffeeOrder = coffeeOrderService.createOrder("Pcz", coffee);
        log.info("update init to paid: {}", coffeeOrderService.updateState(coffeeOrder, OrderState.PAID));
        log.info("update paid to init: {}", coffeeOrderService.updateState(coffeeOrder, OrderState.INIT));
    }
}
