package com.pcz.spring.family.redis.repository.repository;

import com.pcz.spring.family.redis.repository.model.CoffeeCache;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author picongzhi
 */
public interface CoffeeCacheRepository extends CrudRepository<CoffeeCache, Long> {
    Optional<CoffeeCache> findOneByName(String name);
}
