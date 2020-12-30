package com.pcz.spring.family.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author picongzhi
 */
@Slf4j
@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        showConnection();
        showData();
    }

    private void showConnection() {
        try (Connection connection = dataSource.getConnection()) {
            log.info("Datasource: {}", dataSource.toString());
            log.info("Connection: {}", connection.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showData() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM FOO");
        log.info("list: {}", list);
    }
}
