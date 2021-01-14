package com.pcz.spring.family.thymeleaf.view.repository;

import com.pcz.spring.family.thymeleaf.view.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author picongzhi
 */
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
