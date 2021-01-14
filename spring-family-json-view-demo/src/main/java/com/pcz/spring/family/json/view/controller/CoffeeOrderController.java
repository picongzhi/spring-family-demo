package com.pcz.spring.family.json.view.controller;

import com.pcz.spring.family.json.view.controller.request.NewOrderRequest;
import com.pcz.spring.family.json.view.model.Coffee;
import com.pcz.spring.family.json.view.model.CoffeeOrder;
import com.pcz.spring.family.json.view.service.CoffeeOrderService;
import com.pcz.spring.family.json.view.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/order")
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
    public CoffeeOrder create(NewOrderRequest request) {
        log.info("Order request: {}", request);
        Coffee[] coffees = coffeeService.getCoffeeByNames(request.getItems())
                .toArray(new Coffee[]{});

        return coffeeOrderService.createOrder(request.getCustomer(), coffees);
    }
}
