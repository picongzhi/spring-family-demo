package com.pcz.spring.family.redis.repository.repository;

import com.pcz.spring.family.redis.repository.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author picongzhi
 */
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
