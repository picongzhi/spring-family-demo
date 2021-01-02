package com.pcz.spring.family.jedis.repository;

import com.pcz.spring.family.jedis.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author picongzhi
 */
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
