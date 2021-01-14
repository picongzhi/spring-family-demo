package com.pcz.spring.family.thymeleaf.view.service;

import com.pcz.spring.family.thymeleaf.view.model.Coffee;
import com.pcz.spring.family.thymeleaf.view.model.CoffeeOrder;
import com.pcz.spring.family.thymeleaf.view.model.OrderState;
import com.pcz.spring.family.thymeleaf.view.repository.CoffeeOrderRepository;
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

    public CoffeeOrder get(Long id) {
        return coffeeOrderRepository.getOne(id);
    }

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
            log.warn("Wrong state: {}, {}", state, coffeeOrder.getState());
            return false;
        }

        coffeeOrder.setState(state);
        coffeeOrderRepository.save(coffeeOrder);
        log.info("Update order: {}", coffeeOrder);

        return true;
    }
}
