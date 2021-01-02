package com.pcz.spring.family.cache.redis.repository;

import com.pcz.spring.family.cache.redis.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author picongzhi
 */
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
