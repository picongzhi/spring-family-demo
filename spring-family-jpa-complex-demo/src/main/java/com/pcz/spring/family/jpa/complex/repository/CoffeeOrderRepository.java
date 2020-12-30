package com.pcz.spring.family.jpa.complex.repository;

import com.pcz.spring.family.jpa.complex.model.CoffeeOrder;

import java.util.List;

/**
 * @author picongzhi
 */
public interface CoffeeOrderRepository extends BaseRepository<CoffeeOrder, Long> {
    List<CoffeeOrder> findByCustomerOrderById(String customer);

    List<CoffeeOrder> findByItemsName(String name);
}
