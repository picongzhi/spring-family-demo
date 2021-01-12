package com.pcz.spring.family.complex.controller.repository;

import com.pcz.spring.family.complex.controller.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author picongzhi
 */
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
    List<Coffee> findByNameInOrderById(List<String> names);

    Coffee findByName(String name);
}
