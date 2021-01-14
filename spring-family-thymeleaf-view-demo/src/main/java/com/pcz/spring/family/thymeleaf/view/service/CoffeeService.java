package com.pcz.spring.family.thymeleaf.view.service;

import com.pcz.spring.family.thymeleaf.view.model.Coffee;
import com.pcz.spring.family.thymeleaf.view.repository.CoffeeRepository;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author picongzhi
 */
@Service
@CacheConfig(cacheNames = "CoffeeCache")
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;

    public Coffee saveCoffee(String name, Money price) {
        return coffeeRepository.save(Coffee.builder()
                .name(name)
                .price(price)
                .build());
    }

    @Cacheable
    public List<Coffee> getAllCoffee() {
        return coffeeRepository.findAll(Sort.by("id"));
    }

    public Coffee getCoffee(Long id) {
        return coffeeRepository.getOne(id);
    }

    public Coffee getCoffee(String name) {
        return coffeeRepository.findByName(name);
    }

    public List<Coffee> getCoffeeByNames(List<String> names) {
        return coffeeRepository.findByNameInOrderById(names);
    }
}
