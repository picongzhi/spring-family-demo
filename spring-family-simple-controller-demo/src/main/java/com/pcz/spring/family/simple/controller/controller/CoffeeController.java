package com.pcz.spring.family.simple.controller.controller;

import com.pcz.spring.family.simple.controller.model.Coffee;
import com.pcz.spring.family.simple.controller.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author picongzhi
 */
@RequestMapping("/coffee")
@Controller
public class CoffeeController {
    @Autowired
    private CoffeeService coffeeService;

    @GetMapping("")
    @ResponseBody
    public List<Coffee> getAll() {
        return coffeeService.getAllCoffees();
    }
}
