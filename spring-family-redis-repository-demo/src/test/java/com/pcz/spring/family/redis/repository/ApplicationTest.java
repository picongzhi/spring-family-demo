package com.pcz.spring.family.redis.repository;

import com.pcz.spring.family.redis.repository.model.Coffee;
import com.pcz.spring.family.redis.repository.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

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
    public void findOneCoffeeTest() {
        Optional<Coffee> optional = coffeeService.findOneCoffee("mocha");
        log.info("coffee: {}", optional);

        optional = coffeeService.findOneCoffee("mocha");
        log.info("coffee from redis: {}", optional);
    }
}
