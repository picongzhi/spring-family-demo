package com.pcz.spring.family.performance.aspect.repository;

import com.pcz.spring.family.performance.aspect.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author picongzhi
 */
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
