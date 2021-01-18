package com.pcz.spring.family.exception.controller;

import com.pcz.spring.family.exception.controller.request.NewOrderRequest;
import com.pcz.spring.family.exception.model.Coffee;
import com.pcz.spring.family.exception.model.CoffeeOrder;
import com.pcz.spring.family.exception.service.CoffeeOrderService;
import com.pcz.spring.family.exception.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @GetMapping("/{id}")
    public CoffeeOrder getOrder(@PathVariable Long id) {
        return coffeeOrderService.get(id);
    }

    @PostMapping(path = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CoffeeOrder create(@RequestBody NewOrderRequest request) {
        log.info("New order request: {}", request);
        Coffee[] coffees = coffeeService.getCoffeeByName(request.getItems()).toArray(new Coffee[]{});

        return coffeeOrderService.createOrder(request.getCustomer(), coffees);
    }
}
