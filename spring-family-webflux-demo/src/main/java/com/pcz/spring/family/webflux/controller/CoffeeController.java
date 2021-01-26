package com.pcz.spring.family.webflux.controller;

import com.pcz.spring.family.webflux.model.Coffee;
import com.pcz.spring.family.webflux.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author picongzhi
 */
@Slf4j
@Controller
@RequestMapping("/coffee")
public class CoffeeController {
    @Autowired
    private CoffeeService coffeeService;

    @GetMapping(path = "", params = "!name")
    @ResponseBody
    public Flux<Coffee> getAll() {
        return coffeeService.findAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Mono<Coffee> getById(@PathVariable Long id) {
        return coffeeService.findById(id);
    }

    @GetMapping(path = "", params = "name")
    @ResponseBody
    public Mono<Coffee> getByName(String name) {
        return coffeeService.findByName(name);
    }
}
