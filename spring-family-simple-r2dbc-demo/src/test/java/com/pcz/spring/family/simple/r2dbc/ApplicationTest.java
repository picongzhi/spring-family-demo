package com.pcz.spring.family.simple.r2dbc;

import com.pcz.spring.family.simple.r2dbc.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.CountDownLatch;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
    @Autowired
    private DatabaseClient databaseClient;

    @Test
    public void contextLoads() {
    }

    @Test
    public void r2dbcTest() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);

        databaseClient.select()
                .from("t_coffee")
                .orderBy(Sort.by(Sort.Direction.DESC, "id"))
                .page(PageRequest.of(0, 3))
                .as(Coffee.class)
                .fetch()
                .all()
                .doFinally(signalType -> countDownLatch.countDown())
                .subscribeOn(Schedulers.elastic())
                .subscribe(coffee -> log.info("coffee: {}", coffee));

        databaseClient.execute("SELECT * FROM t_coffee")
                .as(Coffee.class)
                .fetch()
                .all()
                .doFinally(signalType -> countDownLatch.countDown())
                .subscribeOn(Schedulers.elastic())
                .subscribe(coffee -> log.info("coffee: {}", coffee));

        log.info("started");
        countDownLatch.await();
    }
}
