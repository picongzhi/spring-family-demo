package com.pcz.spring.family.performance.aspect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author picongzhi
 */
@SpringBootApplication
@EnableJpaRepositories
@EnableAspectJAutoProxy
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
