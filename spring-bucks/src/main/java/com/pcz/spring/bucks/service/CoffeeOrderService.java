package com.pcz.spring.bucks.service;

import com.pcz.spring.bucks.model.Coffee;
import com.pcz.spring.bucks.model.CoffeeOrder;
import com.pcz.spring.bucks.model.OrderState;
import com.pcz.spring.bucks.repository.CoffeeOrderRepository;
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

        return coffeeOrderRepository.save(coffeeOrder);
    }

    public boolean updateState(CoffeeOrder coffeeOrder, OrderState state) {
        if (state.compareTo(coffeeOrder.getState()) <= 0) {
            log.warn("Wrong state order: {}, {}", state, coffeeOrder.getState());
            return false;
        }

        coffeeOrder.setState(state);
        coffeeOrderRepository.save(coffeeOrder);

        return true;
    }
}
