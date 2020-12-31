package com.pcz.spring.family.mongodb.repository.config;

import com.pcz.spring.family.mongodb.repository.converter.MoneyReadConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.Arrays;

/**
 * @author picongzhi
 */
@Configuration
public class MongoDbConfig {
    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(Arrays.asList(
                new MoneyReadConverter()));
    }
}
