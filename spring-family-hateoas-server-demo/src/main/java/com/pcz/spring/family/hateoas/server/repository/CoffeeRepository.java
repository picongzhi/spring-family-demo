package com.pcz.spring.family.hateoas.server.repository;

import com.pcz.spring.family.hateoas.server.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * @author picongzhi
 */
@RepositoryRestResource(path = "coffee")
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
    List<Coffee> findByNameInOrderById(List<String> list);

    Coffee findByName(String name);
}
