package com.pcz.spring.family.complex.controller.controller;

import com.pcz.spring.family.complex.controller.controller.request.NewCoffeeRequest;
import com.pcz.spring.family.complex.controller.model.Coffee;
import com.pcz.spring.family.complex.controller.service.CoffeeService;
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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author picongzhi
 */
@Slf4j
@RequestMapping("/coffee")
@Controller
public class CoffeeController {
    @Autowired
    private CoffeeService coffeeService;

    @GetMapping(value = "", params = "!name")
    @ResponseBody
    public List<Coffee> getAll() {
        return coffeeService.getAllCoffees();
    }

    @GetMapping(path = "", params = "name")
    @ResponseBody
    public Coffee getByName(String name) {
        return coffeeService.getCoffee(name);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Coffee getById(@PathVariable Long id) {
        return coffeeService.getCoffee(id);
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Coffee addCoffee(@Valid NewCoffeeRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.warn("Errors: {}", bindingResult);
            return null;
        }

        return coffeeService.saveCoffee(request.getName(), request.getPrice());
    }

//    @PostMapping(path = "", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.CREATED)
//    public Coffee addCoffeeWithoutBindingResult(@Valid NewCoffeeRequest request) {
//        return coffeeService.saveCoffee(request.getName(), request.getPrice());
//    }

    @PostMapping(path = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
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
