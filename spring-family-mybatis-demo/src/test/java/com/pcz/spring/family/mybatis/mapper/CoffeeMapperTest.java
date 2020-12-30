package com.pcz.spring.family.mybatis.mapper;

import com.pcz.spring.family.mybatis.ApplicationTest;
import com.pcz.spring.family.mybatis.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CoffeeMapperTest extends ApplicationTest {
    @Autowired
    private CoffeeMapper coffeeMapper;

    @Test
    public void test() {
        Coffee coffee = Coffee.builder()
                .name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .build();
        int count = coffeeMapper.save(coffee);
        log.info("count: {}, coffee: {}", count, coffee);

        coffee = Coffee.builder()
                .name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 25.0))
                .build();
        count = coffeeMapper.save(coffee);
        log.info("count: {}, coffee: {}", count, coffee);

        coffee = coffeeMapper.findById(coffee.getId());
        log.info("find coffee: {}", coffee);
    }
}
