package com.pcz.spring.family.pure.spring.datasource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

@Configuration
public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "classpath:/applicationContext.xml");

        showBeans(applicationContext);
        showDataSource(applicationContext);
    }

    private static void showBeans(ApplicationContext applicationContext) {
        System.out.println(Arrays.toString(applicationContext.getBeanDefinitionNames()));
    }

    private static void showDataSource(ApplicationContext applicationContext) {
        DataSource dataSource = applicationContext.getBean("dataSource", DataSource.class);
        System.out.println("DataSource: " + dataSource);

        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Connection: " + connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
