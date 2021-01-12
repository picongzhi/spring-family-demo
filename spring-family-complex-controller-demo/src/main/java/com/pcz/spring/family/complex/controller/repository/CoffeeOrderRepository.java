package com.pcz.spring.family.complex.controller.repository;

import com.pcz.spring.family.complex.controller.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author picongzhi
 */
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
