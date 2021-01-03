package com.pcz.spring.family.redis;

import com.pcz.spring.family.redis.model.Coffee;
import com.pcz.spring.family.redis.service.CoffeeService;
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
    public void redisTest() {
        Optional<Coffee> optional = coffeeService.findOneCoffee("mocha");
        log.info("Coffee: {}", optional);

        optional = coffeeService.findOneCoffee("mocha");
        log.info("coffee from redis: {}", optional);
    }
}
