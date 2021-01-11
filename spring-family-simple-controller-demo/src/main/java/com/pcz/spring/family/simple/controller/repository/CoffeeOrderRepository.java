package com.pcz.spring.family.simple.controller.repository;

import com.pcz.spring.family.simple.controller.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author picongzhi
 */
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
