package com.pcz.spring.family.performance.aspect.repository;

import com.pcz.spring.family.performance.aspect.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author picongzhi
 */
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
