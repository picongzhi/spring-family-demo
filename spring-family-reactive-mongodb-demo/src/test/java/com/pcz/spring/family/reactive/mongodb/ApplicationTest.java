package com.pcz.spring.family.reactive.mongodb;

import com.pcz.spring.family.reactive.mongodb.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void reactiveTest() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        reactiveMongoTemplate.insertAll(initCoffees())
                .publishOn(Schedulers.elastic())
                .doOnNext(coffee -> log.info("next: {}", coffee))
                .doOnComplete(() -> {
                    reactiveMongoTemplate.updateMulti(
                            Query.query(Criteria.where("price").gte(3000L)),
                            new Update()
                                    .inc("price", -500L)
                                    .currentDate("updateTime"),
                            Coffee.class)
                            .doFinally(signalType -> {
                                countDownLatch.countDown();
                                log.info("finally: {}", signalType);
                            })
                            .subscribe(res -> log.info("res: {}", res));
                })
                .doFinally(signalType -> {
                    countDownLatch.countDown();
                    log.info("finally: {}", signalType);
                })
                .count()
                .subscribe(count -> log.info("insert {} coffees", count));
        log.info("start running");
        countDownLatch.await();
    }

    private List<Coffee> initCoffees() {
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

        return Arrays.asList(espresso, latte);
    }
}
