package com.pcz.spring.family.mongodb.repository.repository;

import com.pcz.spring.family.mongodb.repository.model.Coffee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author picongzhi
 */
public interface CoffeeRepository extends MongoRepository<Coffee, String> {
    List<Coffee> findByName(String name);
}
