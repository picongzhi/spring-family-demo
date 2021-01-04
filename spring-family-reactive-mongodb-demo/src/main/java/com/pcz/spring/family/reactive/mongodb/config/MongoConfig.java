package com.pcz.spring.family.reactive.mongodb.config;

import com.pcz.spring.family.reactive.mongodb.converter.MoneyReadConverter;
import com.pcz.spring.family.reactive.mongodb.converter.MoneyWriteConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.Arrays;

/**
 * @author picongzhi
 */
@Configuration
public class MongoConfig {
    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(Arrays.asList(
                new MoneyReadConverter(), new MoneyWriteConverter()));
    }
}
