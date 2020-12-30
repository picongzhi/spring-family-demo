package com.pcz.spring.bucks.repository;

import com.pcz.spring.bucks.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author picongzhi
 */
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
