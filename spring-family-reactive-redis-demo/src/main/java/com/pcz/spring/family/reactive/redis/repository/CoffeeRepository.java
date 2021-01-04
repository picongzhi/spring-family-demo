package com.pcz.spring.family.reactive.redis.repository;

import com.pcz.spring.family.reactive.redis.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author picongzhi
 */
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
