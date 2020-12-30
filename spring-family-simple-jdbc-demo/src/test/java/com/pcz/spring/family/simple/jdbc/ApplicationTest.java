package com.pcz.spring.family.simple.jdbc;

import com.pcz.spring.family.simple.jdbc.dao.BatchFooDao;
import com.pcz.spring.family.simple.jdbc.dao.FooDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
    @Autowired
    private FooDao fooDao;
    @Autowired
    private BatchFooDao batchFooDao;

    @Test
    public void insertTest() {
        fooDao.insert();
    }

    @Test
    public void listTest() {
        fooDao.list();
    }

    @Test
    public void batchInsertTest() {
        batchFooDao.batchInsert();
    }
}
