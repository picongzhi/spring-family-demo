package com.pcz.spring.family.mongodb;

import com.mongodb.client.result.UpdateResult;
import com.pcz.spring.family.mongodb.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void saveTest() {
        Coffee coffee = Coffee.builder()
                .name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        Coffee saved = mongoTemplate.save(coffee);
        log.info("saved coffee: {}", saved);
    }

    @Test
    public void findTest() {
        List<Coffee> coffees = mongoTemplate.find(
                Query.query(Criteria.where("name").is("espresso")),
                Coffee.class);
        log.info("size: {}", coffees.size());
        log.info("coffees: {}", coffees);
    }

    @Test
    public void updateTest() {
        UpdateResult result = mongoTemplate.updateFirst(
                Query.query(Criteria.where("name").is("espresso")),
                new Update()
                        .set("price", Money.ofMajor(CurrencyUnit.of("CNY"), 30))
                        .currentDate("updateTime"),
                Coffee.class);
        log.info("modified count: {}", result.getModifiedCount());
    }
}
