package com.pcz.spring.family.cache.repository;

import com.pcz.spring.family.cache.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author picongzhi
 */
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
