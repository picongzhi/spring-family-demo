package com.pcz.reactive.spring.bucks.repository;

import com.pcz.reactive.spring.bucks.model.CoffeeOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;

/**
 * @author picongzhi
 */
@Repository
public class CoffeeOrderRepository {
    @Autowired
    private DatabaseClient databaseClient;

    public Mono<Long> save(CoffeeOrder coffeeOrder) {
        return databaseClient
                .insert().into("t_order")
                .value("customer", coffeeOrder.getCustomer())
                .value("state", coffeeOrder.getState().ordinal())
                .value("create_time", coffeeOrder.getCreateTime())
                .value("update_time", coffeeOrder.getUpdateTime())
                .fetch()
                .first()
                .flatMap(map -> Mono.just((Long) map.get("ID")))
                .flatMap(id -> Flux.fromIterable(coffeeOrder.getItems())
                        .flatMap(coffee -> databaseClient.insert().into("t_order_coffee")
                                .value("coffee_order_id", id)
                                .value("items_id", coffee.getId())
                                .then())
                        .then(Mono.just(id)));
    }
}
