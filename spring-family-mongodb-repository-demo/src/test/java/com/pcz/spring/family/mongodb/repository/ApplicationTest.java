package com.pcz.spring.family.mongodb.repository;

import com.pcz.spring.family.mongodb.repository.model.Coffee;
import com.pcz.spring.family.mongodb.repository.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
    @Autowired
    private CoffeeRepository coffeeRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void insertTest() {
        Coffee espresso = Coffee.builder()
                .name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        Coffee latte = Coffee.builder()
                .name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 30.0))
                .createTime(new Date())
                .updateTime(new Date())
                .build();

        coffeeRepository.insert(Arrays.asList(espresso, latte));
    }

    @Test
    public void findAllTest() {
        List<Coffee> coffees = coffeeRepository.findAll(Sort.by("name"));
        log.info("size: {}", coffees.size());
        log.info("coffees: {}", coffees);
    }

    @Test
    public void saveTest() {
        List<Coffee> coffees = coffeeRepository.findByName("latte");
        coffees.forEach(coffee -> {
            coffee.setPrice(Money.of(CurrencyUnit.of("CNY"), 35.0));
            coffee.setUpdateTime(new Date());

            coffeeRepository.save(coffee);
        });
    }
}
