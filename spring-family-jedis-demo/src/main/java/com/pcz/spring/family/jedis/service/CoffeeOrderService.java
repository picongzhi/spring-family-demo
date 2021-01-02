package com.pcz.spring.family.jedis.service;

import com.pcz.spring.family.jedis.model.Coffee;
import com.pcz.spring.family.jedis.model.CoffeeOrder;
import com.pcz.spring.family.jedis.model.OrderState;
import com.pcz.spring.family.jedis.repository.CoffeeOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author picongzhi
 */
@Slf4j
@Service
public class CoffeeOrderService {
    @Autowired
    private CoffeeOrderRepository coffeeOrderRepository;

    public CoffeeOrder createOrder(String customer, Coffee... coffees) {
        CoffeeOrder coffeeOrder = CoffeeOrder.builder()
                .customer(customer)
                .items(Arrays.asList(coffees))
                .state(OrderState.INIT)
                .build();
        CoffeeOrder saved = coffeeOrderRepository.save(coffeeOrder);
        log.info("order: {}", saved);

        return saved;
    }
}
