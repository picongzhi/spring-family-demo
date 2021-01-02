package com.pcz.spring.family.cache.repository;

import com.pcz.spring.family.cache.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author picongzhi
 */
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
