package com.pcz.spring.family.simple.jdbc.dao;

import com.pcz.spring.family.simple.jdbc.model.Foo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author picongzhi
 */
@Slf4j
@Repository
public class FooDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SimpleJdbcInsert simpleJdbcInsert;

    public void insert() {
        List<String> bars = Arrays.asList("b", "c");
        bars.forEach(bar -> jdbcTemplate.update("INSERT INTO FOO (BAR) VALUES (?)", bar));

        HashMap<String, String> row = new HashMap<>();
        row.put("BAR", "d");

        Number id = simpleJdbcInsert.executeAndReturnKey(row);
        log.info("id: {}", id.longValue());
    }

    public void list() {
        log.info("Count: {}", jdbcTemplate.queryForObject("SELECT COUNT(*) FROM FOO", Long.class));

        List<String> list = jdbcTemplate.queryForList("SELECT BAR FROM FOO", String.class);
        list.forEach(bar -> log.info("Bar: {}", bar));

        List<Foo> foos = jdbcTemplate.query("SELECT * FROM FOO", new RowMapper<Foo>() {
            @Override
            public Foo mapRow(ResultSet resultSet, int i) throws SQLException {
                return Foo.builder()
                        .id(resultSet.getLong(1))
                        .bar(resultSet.getString(2))
                        .build();
            }
        });
        foos.forEach(foo -> log.info("Foo: {}", foo));
    }
}
