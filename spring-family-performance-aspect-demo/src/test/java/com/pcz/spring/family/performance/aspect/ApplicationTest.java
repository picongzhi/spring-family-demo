package com.pcz.spring.family.performance.aspect;

import com.pcz.spring.family.performance.aspect.model.Coffee;
import com.pcz.spring.family.performance.aspect.model.CoffeeOrder;
import com.pcz.spring.family.performance.aspect.model.OrderState;
import com.pcz.spring.family.performance.aspect.repository.CoffeeRepository;
import com.pcz.spring.family.performance.aspect.service.CoffeeOrderService;
import com.pcz.spring.family.performance.aspect.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private CoffeeService coffeeService;

    @Autowired
    private CoffeeOrderService coffeeOrderService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void performanceAspectTest() {
        List<Coffee> coffees = coffeeRepository.findAll();
        log.info("coffees: {}", coffees);

        Optional<Coffee> latte = coffeeService.findOneCoffee("Latte");
        if (latte.isPresent()) {
            CoffeeOrder coffeeOrder = coffeeOrderService.createOrder("Pcz", latte.get());
            log.info("update INIT to PAID: {}", coffeeOrderService.updateState(coffeeOrder, OrderState.PAID));
            log.info("update PAID to INIT: {}", coffeeOrderService.updateState(coffeeOrder, OrderState.INIT));
        }
    }
}
