package com.pcz.spring.family.performance.aspect.service;

import com.pcz.spring.family.performance.aspect.model.Coffee;
import com.pcz.spring.family.performance.aspect.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author picongzhi
 */
@Slf4j
@Service
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;

    public Optional<Coffee> findOneCoffee(String name) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.ignoreCase());
        return coffeeRepository
                .findOne(Example.of(
                        Coffee.builder()
                                .name(name)
                                .build(),
                        exampleMatcher));
    }
}
