package com.pcz.spring.family.redis.repository.repository;

import com.pcz.spring.family.redis.repository.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author picongzhi
 */
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
