package com.pcz.spring.family.webflux.service;

import com.pcz.spring.family.webflux.model.CoffeeOrder;
import com.pcz.spring.family.webflux.model.OrderState;
import com.pcz.spring.family.webflux.repository.CoffeeOrderRepository;
import com.pcz.spring.family.webflux.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;

/**
 * @author picongzhi
 */
@Service
public class CoffeeOrderService {
    @Autowired
    private CoffeeOrderRepository coffeeOrderRepository;
    @Autowired
    private CoffeeRepository coffeeRepository;

    public Mono<CoffeeOrder> getById(Long id) {
        return coffeeOrderRepository.get(id);
    }

    public Mono<Long> create(String customer, List<String> items) {
        return Flux.fromStream(items.stream())
                .flatMap(name -> coffeeRepository.findByName(name))
                .collectList()
                .flatMap(coffees -> coffeeOrderRepository.save(CoffeeOrder.builder()
                        .customer(customer)
                        .items(coffees)
                        .state(OrderState.INIT)
                        .createTime(new Date())
                        .updateTime(new Date())
                        .build()));
    }
}
