package com.pcz.spring.family.mybatis.generator;

import com.pcz.spring.family.mybatis.generator.mapper.CoffeeMapper;
import com.pcz.spring.family.mybatis.generator.model.Coffee;
import com.pcz.spring.family.mybatis.generator.model.CoffeeExample;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
    @Autowired
    private CoffeeMapper coffeeMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void generateTest() throws Exception {
        List<String> warnings = new ArrayList<>();

        ConfigurationParser configurationParser = new ConfigurationParser(warnings);
        Configuration configuration = configurationParser.parseConfiguration(
                getClass().getClassLoader().getResourceAsStream("generatorConfig.xml"));

        DefaultShellCallback defaultShellCallback = new DefaultShellCallback(true);

        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration, defaultShellCallback, warnings);
        myBatisGenerator.generate(null);
        log.info("hhh");
    }

    @Test
    public void coffeeMapperTest() {
        Coffee espresso = new Coffee()
                .withName("espresso")
                .withPrice(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .withCreateTime(new Date())
                .withUpdateTime(new Date());
        coffeeMapper.insert(espresso);

        Coffee latte = new Coffee()
                .withName("latte")
                .withPrice(Money.of(CurrencyUnit.of("CNY"), 30.0))
                .withCreateTime(new Date())
                .withUpdateTime(new Date());
        coffeeMapper.insert(latte);

        Coffee coffee = coffeeMapper.selectByPrimaryKey(1L);
        log.info("coffee: {}", coffee);

        CoffeeExample coffeeExample = new CoffeeExample();
        coffeeExample.createCriteria().andNameEqualTo("latte");
        List<Coffee> coffees = coffeeMapper.selectByExample(coffeeExample);
        log.info("coffees: {}", coffees);
    }
}
