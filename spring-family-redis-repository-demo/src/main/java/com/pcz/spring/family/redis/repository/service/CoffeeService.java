package com.pcz.spring.family.redis.repository.service;

import com.pcz.spring.family.redis.repository.model.Coffee;
import com.pcz.spring.family.redis.repository.model.CoffeeCache;
import com.pcz.spring.family.redis.repository.repository.CoffeeCacheRepository;
import com.pcz.spring.family.redis.repository.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author picongzhi
 */
@Slf4j
@Service
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private CoffeeCacheRepository coffeeCacheRepository;

    public List<Coffee> findAllCoffee() {
        return coffeeRepository.findAll();
    }

    public Optional<Coffee> findOneCoffee(String name) {
        Optional<CoffeeCache> coffeeCacheOptional = coffeeCacheRepository.findOneByName(name);
        if (coffeeCacheOptional.isPresent()) {
            CoffeeCache coffeeCache = coffeeCacheOptional.get();
            Coffee coffee = Coffee.builder()
                    .name(coffeeCache.getName())
                    .price(coffeeCache.getPrice())
                    .build();
            log.info("Coffee {} found in cache.", coffeeCache);

            return Optional.of(coffee);
        }

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.exact().ignoreCase());
        Optional<Coffee> optional = coffeeRepository.findOne(
                Example.of(Coffee.builder().name(name).build(), exampleMatcher));
        log.info("Coffee found: {}", optional);

        optional.ifPresent(coffee -> {
            CoffeeCache coffeeCache = CoffeeCache.builder()
                    .id(coffee.getId())
                    .name(coffee.getName())
                    .price(coffee.getPrice())
                    .build();
            log.info("Save coffee {} to cache.", coffeeCache);
            coffeeCacheRepository.save(coffeeCache);
        });

        return optional;
    }
}
