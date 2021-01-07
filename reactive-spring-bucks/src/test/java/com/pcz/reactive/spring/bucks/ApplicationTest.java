package com.pcz.reactive.spring.bucks;

import com.pcz.reactive.spring.bucks.model.Coffee;
import com.pcz.reactive.spring.bucks.model.CoffeeOrder;
import com.pcz.reactive.spring.bucks.model.OrderState;
import com.pcz.reactive.spring.bucks.service.CoffeeOrderService;
import com.pcz.reactive.spring.bucks.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
    @Autowired
    private CoffeeService coffeeService;

    @Autowired
    private CoffeeOrderService coffeeOrderService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void initCacheTest() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        coffeeService.initCache()
                .then(coffeeService.findOneCoffee("mocha")
                        .flatMap(coffee -> {
                            CoffeeOrder coffeeOrder = coffeeOrder("Pcz", coffee);
                            return coffeeOrderService.create(coffeeOrder);
                        })
                        .doOnError(t -> log.error("error", t)))
                .subscribe(order -> {
                    log.info("create order: {}", order);
                    countDownLatch.countDown();
                });

        log.info("subscribed");
        countDownLatch.await();
    }

    private CoffeeOrder coffeeOrder(String customer, Coffee... coffees) {
        return CoffeeOrder.builder()
                .customer(customer)
                .items(Arrays.asList(coffees))
                .state(OrderState.INIT)
                .createTime(new Date())
                .updateTime(new Date())
                .build();
    }
}
