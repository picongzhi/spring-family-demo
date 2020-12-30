package com.pcz.spring.family.druid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author picongzhi
 */
@Slf4j
@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private void showConnection() {
        try (Connection connection = dataSource.getConnection()) {
            log.info("Datasource: {}", dataSource.toString());
            log.info("Connection: {}", connection.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(String... args) throws Exception {
        showConnection();
    }
}
