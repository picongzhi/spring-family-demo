package com.pcz.spring.family.r2dbc.repository;

import com.pcz.spring.family.r2dbc.repository.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;

import java.util.concurrent.CountDownLatch;

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
    public void repositoryTest() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);

        coffeeRepository.findAllById(Flux.just(1L, 2L))
                .map(coffee -> coffee.getName() + ":" + coffee.getPrice().toString())
                .doFinally(signalType -> countDownLatch.countDown())
                .subscribe(coffee -> log.info("coffee: {}", coffee));
        coffeeRepository.findByName("mocha")
                .doFinally(signalType -> countDownLatch.countDown())
                .subscribe(coffee -> log.info("coffee: {}", coffee));

        countDownLatch.await();
    }
}
