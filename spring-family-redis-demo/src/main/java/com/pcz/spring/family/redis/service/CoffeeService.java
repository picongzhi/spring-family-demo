package com.pcz.spring.family.redis.service;

import com.pcz.spring.family.redis.model.Coffee;
import com.pcz.spring.family.redis.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
    private static final String CACHE = "springbucks-coffee";

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private RedisTemplate<String, Coffee> redisTemplate;

    public List<Coffee> findAllCoffee() {
        return coffeeRepository.findAll();
    }

    public Optional<Coffee> findOneCoffee(String name) {
        HashOperations<String, String, Coffee> hashOperations = redisTemplate.opsForHash();
        if (redisTemplate.hasKey(CACHE) && hashOperations.hasKey(CACHE, name)) {
            log.info("get coffee {} from redis.", name);
            return Optional.of(hashOperations.get(CACHE, name));
        }

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.exact().ignoreCase());
        Optional<Coffee> optional = coffeeRepository.findOne(
                Example.of(Coffee.builder().name(name).build(), exampleMatcher));
        log.info("Coffee found: {}", optional);
        if (optional.isPresent()) {
            log.info("Put coffee {} to redis", name);
            hashOperations.put(CACHE, name, optional.get());
            redisTemplate.expire(CACHE, 1, TimeUnit.MINUTES);
        }

        return optional;
    }
}
