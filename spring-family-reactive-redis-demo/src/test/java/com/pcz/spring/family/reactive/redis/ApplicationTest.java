package com.pcz.spring.family.reactive.redis;

import com.pcz.spring.family.reactive.redis.model.Coffee;
import com.pcz.spring.family.reactive.redis.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
    private static final String KEY = "coffee_menu";

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private ReactiveStringRedisTemplate reactiveStringRedisTemplate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void reactiveTest() throws InterruptedException {
        ReactiveHashOperations<String, String, String> hashOperations = reactiveStringRedisTemplate.opsForHash();
        CountDownLatch countDownLatch = new CountDownLatch(1);

        List<Coffee> coffees = coffeeRepository.findAll();
        log.info("coffees: {}", coffees);

        Flux.fromIterable(coffees)
                .publishOn(Schedulers.single())
                .doOnComplete(() -> log.info("publish done"))
                .flatMap(coffee -> {
                    log.info("try to put {}, {}", coffee.getName(), coffee.getPrice());
                    return hashOperations.put(KEY, coffee.getName(), coffee.getPrice().toString());
                })
                .doOnComplete(() -> log.info("save redis done"))
                .concatWith(reactiveStringRedisTemplate.expire(KEY, Duration.ofMinutes(1)))
                .doOnComplete(() -> log.info("expire done"))
                .onErrorResume(err -> {
                    log.error("exception: {}", err.getMessage());
                    return Mono.just(false);
                })
                .subscribe(b -> log.info("res: {}", b),
                        err -> log.error("exception: {}", err.getMessage()),
                        () -> countDownLatch.countDown());
        log.info("waiting");
        countDownLatch.await();
    }
}
