package com.pcz.reactive.spring.bucks.repository;

import com.pcz.reactive.spring.bucks.model.Coffee;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

/**
 * @author picongzhi
 */
public interface CoffeeRepository extends R2dbcRepository<Coffee, Long> {
    @Query("SELECT * FROM t_coffee WHERE name = $1")
    Mono<Coffee> findByName(String name);
}
