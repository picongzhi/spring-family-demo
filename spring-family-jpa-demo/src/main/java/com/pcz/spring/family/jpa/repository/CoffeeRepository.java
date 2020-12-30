package com.pcz.spring.family.jpa.repository;

import com.pcz.spring.family.jpa.model.Coffee;
import org.springframework.data.repository.CrudRepository;

/**
 * @author picongzhi
 */
public interface CoffeeRepository extends CrudRepository<Coffee, Long> {
}
