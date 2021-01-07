package com.pcz.spring.family.performance.aspect.service;

import com.pcz.spring.family.performance.aspect.model.Coffee;
import com.pcz.spring.family.performance.aspect.model.CoffeeOrder;
import com.pcz.spring.family.performance.aspect.model.OrderState;
import com.pcz.spring.family.performance.aspect.repository.CoffeeOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * @author picongzhi
 */
@Slf4j
@Service
@Transactional
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
