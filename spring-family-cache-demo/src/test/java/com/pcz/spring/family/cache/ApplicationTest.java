package com.pcz.spring.family.cache;

import com.pcz.spring.family.cache.model.Coffee;
import com.pcz.spring.family.cache.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
    @Autowired
    private CoffeeService coffeeService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void cacheTest() {
        List<Coffee> coffees = coffeeService.findAllCoffee();
        log.info("coffees size: {}", coffees.size());

        log.info("loading from cache");
        coffees = coffeeService.findAllCoffee();

        coffeeService.reloadCoffee();
        log.info("clear cache");

        coffees = coffeeService.findAllCoffee();
        log.info("coffees size: {}", coffees.size());
    }
}
