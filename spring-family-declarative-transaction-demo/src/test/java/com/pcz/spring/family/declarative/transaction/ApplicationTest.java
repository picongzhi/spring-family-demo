package com.pcz.spring.family.declarative.transaction;

import com.pcz.spring.family.declarative.transaction.exception.RollbackException;
import com.pcz.spring.family.declarative.transaction.service.FooService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
    @Autowired
    private FooService fooService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void insertTest() {
        fooService.insert();
        int count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM FOO WHERE BAR = 'AAA'", Integer.class);
        log.info("count: {}", count);
        Assert.assertEquals(1, count);
    }

    @Test
    public void insertThenRollbackTest() {
        try {
            fooService.insertThenRollback();
        } catch (RollbackException e) {
            int count = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM FOO WHERE BAR = 'BBB'", Integer.class);
            log.info("count: {}", count);
            Assert.assertNotEquals(1, count);
        }
    }

    @Test
    public void invokeInsertThenRollbackTest() {
        try {
            fooService.invokeInsertThenRollback();
        } catch (RollbackException e) {
            int count = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM FOO WHERE BAR = 'BBB'", Integer.class);
            log.info("count: {}", count);
            Assert.assertEquals(1, count);
        }
    }
}
