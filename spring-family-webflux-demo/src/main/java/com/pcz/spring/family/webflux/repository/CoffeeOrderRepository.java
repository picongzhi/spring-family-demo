package com.pcz.spring.family.webflux.repository;

import com.pcz.spring.family.webflux.model.Coffee;
import com.pcz.spring.family.webflux.model.CoffeeOrder;
import com.pcz.spring.family.webflux.model.OrderState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author picongzhi
 */
@Repository
public class CoffeeOrderRepository {
    @Autowired
    private DatabaseClient databaseClient;

    public Mono<CoffeeOrder> get(Long id) {
        return databaseClient
                .execute("SELECT * FROM t_order WHERE id = " + id)
                .map((row, metadata) -> CoffeeOrder.builder()
                        .id(id)
                        .customer(row.get("customer", String.class))
                        .state(OrderState.values()[row.get("state", Integer.class)])
                        .createTime(row.get("create_time", Date.class))
                        .updateTime(row.get("update_time", Date.class))
                        .items(new ArrayList<>())
                        .build())
                .first()
                .flatMap(coffeeOrder -> databaseClient
                        .execute("SELECT c.* FROM t_coffee c, r_order_coffee oc WHERE c.id = oc.items_id AND oc.coffee_order_id = " + id)
                        .as(Coffee.class)
                        .fetch()
                        .all()
                        .collectList()
                        .flatMap(coffees -> {
                            coffeeOrder.getItems().addAll(coffees);
                            return Mono.just(coffeeOrder);
                        }));
    }

    public Mono<Long> save(CoffeeOrder coffeeOrder) {
        return databaseClient
                .insert()
                .into("t_order")
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
