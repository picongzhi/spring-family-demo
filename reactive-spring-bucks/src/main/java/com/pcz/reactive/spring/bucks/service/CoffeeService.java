package com.pcz.reactive.spring.bucks.service;

import com.pcz.reactive.spring.bucks.model.Coffee;
import com.pcz.reactive.spring.bucks.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @author picongzhi
 */
@Slf4j
@Service
public class CoffeeService {
    private static final String PREFIX = "springbucks-";

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private ReactiveRedisTemplate<String, Coffee> reactiveRedisTemplate;

    public Flux<Boolean> initCache() {
        return coffeeRepository.findAll()
                .flatMap(coffee -> reactiveRedisTemplate.opsForValue()
                        .set(PREFIX + coffee.getName(), coffee)
                        .flatMap(b -> reactiveRedisTemplate.expire(PREFIX + coffee.getName(), Duration.ofMinutes(1)))
                        .doOnSuccess(b -> log.info("Loading and caching: {}", coffee)));
    }

    public Mono<Coffee> findOneCoffee(String name) {
        return reactiveRedisTemplate.opsForValue()
                .get(PREFIX + name)
                .switchIfEmpty(coffeeRepository.findByName(name)
                        .doOnSuccess(coffee -> log.info("Loading {} from DB", name)));
    }
}
