package com.pcz.spring.bucks.service;

import com.pcz.spring.bucks.ApplicationTest;
import com.pcz.spring.bucks.model.Coffee;
import com.pcz.spring.bucks.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Slf4j
public class CoffeeServiceTest extends ApplicationTest {
    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private CoffeeService coffeeService;

    @Test
    public void findAllTest() {
        List<Coffee> coffees = coffeeRepository.findAll();
        log.info("size: {}", coffees.size());
        log.info("coffees: {}", coffees);
    }

    @Test
    public void findOneTest() {
        Optional<Coffee> optional = coffeeService.findOneCoffee("Latte");
        optional.ifPresent(coffee -> log.info("coffee: {}", coffee));
    }
}
