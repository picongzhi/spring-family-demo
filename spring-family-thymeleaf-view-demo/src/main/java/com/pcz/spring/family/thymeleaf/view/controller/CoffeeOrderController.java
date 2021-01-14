package com.pcz.spring.family.thymeleaf.view.controller;

import com.pcz.spring.family.thymeleaf.view.model.Coffee;
import com.pcz.spring.family.thymeleaf.view.model.CoffeeOrder;
import com.pcz.spring.family.thymeleaf.view.service.CoffeeOrderService;
import com.pcz.spring.family.thymeleaf.view.service.CoffeeService;
import com.pcz.spring.family.thymeleaf.view.controller.request.NewOrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/order")
public class CoffeeOrderController {
    @Autowired
    private CoffeeService coffeeService;
    @Autowired
    private CoffeeOrderService coffeeOrderService;

    @GetMapping("/{id}")
    @ResponseBody
    public CoffeeOrder getOrder(@PathVariable Long id) {
        return coffeeOrderService.get(id);
    }

    @PostMapping(path = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public CoffeeOrder create(NewOrderRequest request) {
        log.info("Order request: {}", request);
        Coffee[] coffees = coffeeService.getCoffeeByNames(request.getItems())
                .toArray(new Coffee[]{});

        return coffeeOrderService.createOrder(request.getCustomer(), coffees);
    }

    @ModelAttribute
    public List<Coffee> coffeeList() {
        return coffeeService.getAllCoffee();
    }

    @GetMapping(path = "")
    public ModelAndView showCreateForm() {
        return new ModelAndView("create-order-form");
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createOrder(@Valid NewOrderRequest request, BindingResult result, ModelMap map) {
        if (result.hasErrors()) {
            log.warn("Errors: {}", result.getAllErrors());
            map.addAttribute("message", result.toString());
            return "create-order-form";
        }

        log.info("Order request: {}", request);
        Coffee[] coffees = coffeeService.getCoffeeByNames(request.getItems())
                .toArray(new Coffee[]{});
        CoffeeOrder coffeeOrder = coffeeOrderService.createOrder(request.getCustomer(), coffees);

        return "redirect:/order/" + coffeeOrder.getId();
    }
}
