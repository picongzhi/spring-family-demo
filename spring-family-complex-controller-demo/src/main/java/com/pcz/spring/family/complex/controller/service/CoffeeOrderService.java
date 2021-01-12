package com.pcz.spring.family.complex.controller.service;

import com.pcz.spring.family.complex.controller.model.Coffee;
import com.pcz.spring.family.complex.controller.model.CoffeeOrder;
import com.pcz.spring.family.complex.controller.model.OrderState;
import com.pcz.spring.family.complex.controller.repository.CoffeeOrderRepository;
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
        log.info("Saved order: {}", saved);

        return saved;
    }

    public boolean updateState(CoffeeOrder coffeeOrder, OrderState state) {
        if (state.compareTo(coffeeOrder.getState()) <= 0) {
            log.warn("Wrong state order: {} -> {}", state, coffeeOrder.getState());
            return false;
        }

        coffeeOrder.setState(state);
        coffeeOrderRepository.save(coffeeOrder);

        return true;
    }

    public CoffeeOrder get(Long id) {
        return coffeeOrderRepository.getOne(id);
    }
}
