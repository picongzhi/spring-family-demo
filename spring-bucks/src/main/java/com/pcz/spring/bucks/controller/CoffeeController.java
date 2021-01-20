package com.pcz.spring.bucks.controller;

import com.pcz.spring.bucks.controller.request.NewCoffeeRequest;
import com.pcz.spring.bucks.model.Coffee;
import com.pcz.spring.bucks.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author picongzhi
 */
@Slf4j
@RequestMapping("/coffee")
@RestController
public class CoffeeController {
    @Autowired
    private CoffeeService coffeeService;

    @GetMapping(value = "", params = "!name")
    public List<Coffee> getAll() {
        return coffeeService.getAllCoffees();
    }

    @GetMapping(path = "", params = "name")
    public Coffee getByName(String name) {
        return coffeeService.getCoffee(name);
    }

    @GetMapping(path = "/{id}")
    public Coffee getById(@PathVariable Long id) {
        Coffee coffee = coffeeService.getCoffee(id);
        log.info("coffee: {}", coffee);
        return coffee;
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Coffee addJsonCoffeeWithoutBindingResult(@Valid @RequestBody NewCoffeeRequest request) {
        return coffeeService.saveCoffee(request.getName(), request.getPrice());
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Coffee addCoffeeWithoutBindingResult(@Valid NewCoffeeRequest request) {
        return coffeeService.saveCoffee(request.getName(), request.getPrice());
    }

    @PostMapping(path = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public List<Coffee> batchAddCoffee(@RequestParam("file") MultipartFile file) {
        List<Coffee> coffees = new ArrayList<>();
        if (!file.isEmpty()) {
            try (BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(file.getInputStream()))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] arr = StringUtils.split(line, " ");
                    if (arr != null && arr.length == 2) {
                        coffees.add(coffeeService.saveCoffee(
                                arr[0],
                                Money.of(CurrencyUnit.of("CNY"), NumberUtils.createBigDecimal(arr[1]))));
                    }
                }
            } catch (IOException e) {
                log.error("Read file error", e);
            }
        }

        return coffees;
    }
}
