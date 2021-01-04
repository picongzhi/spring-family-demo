package com.pcz.spring.family.simple.reactor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * @author picongzhi
 */
@Slf4j
@SpringBootApplication
public class Application implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Flux.range(1, 6)
                .doOnRequest(n -> log.info("Request {} number", n))
                .publishOn(Schedulers.elastic())
                .doOnComplete(() -> log.info("Publisher complete 1"))
                .map(i -> {
                    log.info("Publish {}, {}", Thread.currentThread(), i);
//                    return 10 / (i - 3);
                    return i;
                })
                .doOnComplete(() -> log.info("Publisher complete 2"))
                .subscribe(i -> log.info("Subscribe {}: {}", Thread.currentThread(), i),
                        e -> log.error("error: {}", e.toString()),
                        () -> log.info("Subscriber complete"));
    }
}
