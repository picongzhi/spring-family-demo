package com.pcz.spring.family.hateoas.client;

import com.pcz.spring.family.hateoas.client.model.Coffee;
import com.pcz.spring.family.hateoas.client.model.CoffeeOrder;
import com.pcz.spring.family.hateoas.client.model.OrderState;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationTest {
    private static final URI ROOT_URI = URI.create("http://localhost:8080");

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void hateoasClientTest() {
        Link coffeeLink = getLink(ROOT_URI, "coffees");
        readCoffeeMenu(coffeeLink);
        EntityModel<Coffee> entityModel = addCoffee(coffeeLink);

        Link orderLink = getLink(ROOT_URI, "coffeeOrders");
        addOrder(orderLink, entityModel);
        queryOrders(orderLink);
    }

    private Link getLink(URI uri, String rel) {
        ResponseEntity<CollectionModel<Link>> responseEntity =
                restTemplate.exchange(uri, HttpMethod.GET, null,
                        new ParameterizedTypeReference<CollectionModel<Link>>() {
                        });
        Link link = responseEntity.getBody().getLink(rel).get();
        log.info("link: {}", link);

        return link;
    }

    private void readCoffeeMenu(Link link) {
        ResponseEntity<PagedModel<EntityModel<Coffee>>> responseEntity =
                restTemplate.exchange(link.getTemplate().expand(), HttpMethod.GET, null,
                        new ParameterizedTypeReference<PagedModel<EntityModel<Coffee>>>() {
                        });
        log.info("Coffees: {}", responseEntity.getBody());
    }

    private EntityModel<Coffee> addCoffee(Link link) {
        Coffee coffee = Coffee.builder()
                .name("Americano")
                .price(Money.of(CurrencyUnit.of("CNY"), 25.0))
                .build();
        RequestEntity<Coffee> requestEntity = RequestEntity
                .post(link.getTemplate().expand())
                .body(coffee);
        ResponseEntity<EntityModel<Coffee>> responseEntity = restTemplate.exchange(requestEntity,
                new ParameterizedTypeReference<EntityModel<Coffee>>() {
                });
        log.info("Add coffee response: {}", responseEntity);

        return responseEntity.getBody();
    }

    private void addOrder(Link link, EntityModel<Coffee> entityModel) {
        CoffeeOrder coffeeOrder = CoffeeOrder.builder()
                .customer("Pcz")
                .state(OrderState.INIT)
                .build();
        RequestEntity<CoffeeOrder> requestEntity = RequestEntity
                .post(link.getTemplate().expand())
                .body(coffeeOrder);
        ResponseEntity<EntityModel<CoffeeOrder>> responseEntity = restTemplate.exchange(requestEntity,
                new ParameterizedTypeReference<EntityModel<CoffeeOrder>>() {
                });
        log.info("Add order response: {}", responseEntity);

        EntityModel<CoffeeOrder> body = responseEntity.getBody();
        Link itemsLink = body.getLink("items").get();
        ResponseEntity<String> addItemResponse = restTemplate.exchange(RequestEntity
                .post(itemsLink.getTemplate().expand())
                .body(Collections.singletonMap("_links", entityModel.getLink("self"))), String.class);
        log.info("Add order items response: {}", addItemResponse);
    }

    private void queryOrders(Link link) {
        ResponseEntity<String> responseEntity = restTemplate
                .getForEntity(link.getTemplate().expand(), String.class);
        log.info("Order response: {}", responseEntity);
    }
}
