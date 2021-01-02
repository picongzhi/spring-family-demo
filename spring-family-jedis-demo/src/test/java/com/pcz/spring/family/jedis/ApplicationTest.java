package com.pcz.spring.family.jedis;

import com.pcz.spring.family.jedis.model.Coffee;
import com.pcz.spring.family.jedis.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
    @Autowired
    private JedisPoolConfig jedisPoolConfig;

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private CoffeeService coffeeService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void jedisPoolConfigTest() {
        log.info("config: {}", jedisPoolConfig.toString());
    }

    @Test
    public void jedisPoolTest() {
        String key = "springbucks-menu";
        try (Jedis jedis = jedisPool.getResource()) {
            List<Coffee> coffees = coffeeService.findAllCoffee();
            coffees.forEach(coffee -> {
                jedis.hset(key,
                        coffee.getName(),
                        Long.toString(coffee.getPrice().getAmountMinorLong()));
            });

            Map<String, String> menu = jedis.hgetAll(key);
            log.info("menu: {}", menu);

            String price = jedis.hget(key, "espresso");
            log.info("espresso: {}",
                    Money.ofMinor(CurrencyUnit.of("CNY"), Long.parseLong(price)));
        }
    }
}
