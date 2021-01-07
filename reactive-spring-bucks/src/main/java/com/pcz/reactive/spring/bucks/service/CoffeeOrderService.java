package com.pcz.reactive.spring.bucks.service;

import com.pcz.reactive.spring.bucks.model.CoffeeOrder;
import com.pcz.reactive.spring.bucks.repository.CoffeeOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author picongzhi
 */
@Slf4j
@Service
public class CoffeeOrderService {
    @Autowired
    private CoffeeOrderRepository coffeeOrderRepository;

    public Mono<Long> create(CoffeeOrder coffeeOrder) {
        return coffeeOrderRepository.save(coffeeOrder);
    }
}
