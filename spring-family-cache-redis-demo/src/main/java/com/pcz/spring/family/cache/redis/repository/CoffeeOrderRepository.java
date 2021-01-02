package com.pcz.spring.family.cache.redis.repository;

import com.pcz.spring.family.cache.redis.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author picongzhi
 */
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
