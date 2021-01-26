package com.pcz.spring.family.webflux.controller;

import com.pcz.spring.family.webflux.controller.request.NewOrderRequest;
import com.pcz.spring.family.webflux.model.CoffeeOrder;
import com.pcz.spring.family.webflux.service.CoffeeOrderService;
import com.pcz.spring.family.webflux.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @author picongzhi
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class CoffeeOrderController {
    @Autowired
    private CoffeeOrderService coffeeOrderService;

    @GetMapping("/{id}")
    public Mono<CoffeeOrder> getOrder(@PathVariable Long id) {
        return coffeeOrderService.getById(id);
    }

    @PostMapping(path = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CoffeeOrder> create(@RequestBody NewOrderRequest request) {
        return coffeeOrderService.create(request.getCustomer(), request.getItems())
                .flatMap(id -> coffeeOrderService.getById(id));
    }
}
