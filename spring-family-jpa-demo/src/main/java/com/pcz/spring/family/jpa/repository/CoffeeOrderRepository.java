package com.pcz.spring.family.jpa.repository;

import com.pcz.spring.family.jpa.model.CoffeeOrder;
import org.springframework.data.repository.CrudRepository;

/**
 * @author picongzhi
 */
public interface CoffeeOrderRepository extends CrudRepository<CoffeeOrder, Long> {
}
