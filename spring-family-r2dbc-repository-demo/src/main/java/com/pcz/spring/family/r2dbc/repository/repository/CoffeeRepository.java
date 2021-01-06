package com.pcz.spring.family.r2dbc.repository.repository;

import com.pcz.spring.family.r2dbc.repository.model.Coffee;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

/**
 * @author picongzhi
 */
public interface CoffeeRepository extends ReactiveCrudRepository<Coffee, Long> {
    @Query("SELECT * FROM t_coffee WHERE name = $1")
    Flux<Coffee> findByName(String name);
}
