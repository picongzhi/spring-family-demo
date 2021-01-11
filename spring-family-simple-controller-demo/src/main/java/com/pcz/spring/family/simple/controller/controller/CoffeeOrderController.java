package com.pcz.spring.family.simple.controller.controller;

import com.pcz.spring.family.simple.controller.controller.request.NewOrderRequest;
import com.pcz.spring.family.simple.controller.model.Coffee;
import com.pcz.spring.family.simple.controller.model.CoffeeOrder;
import com.pcz.spring.family.simple.controller.service.CoffeeOrderService;
import com.pcz.spring.family.simple.controller.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author picongzhi
 */
@Slf4j
@RequestMapping("/order")
@RestController
public class CoffeeOrderController {
    @Autowired
    private CoffeeService coffeeService;

    @Autowired
    private CoffeeOrderService coffeeOrderService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public CoffeeOrder create(@RequestBody NewOrderRequest request) {
        log.info("Order request: {}", request);
        Coffee[] coffees = coffeeService.getCoffeeByName(request.getItems()).toArray(new Coffee[]{});

        return coffeeOrderService.createOrder(request.getCustomer(), coffees);
    }
}
