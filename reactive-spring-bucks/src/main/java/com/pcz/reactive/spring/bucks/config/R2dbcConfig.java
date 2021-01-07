package com.pcz.reactive.spring.bucks.config;

import com.pcz.reactive.spring.bucks.converter.DateToLocalDatetimeConverter;
import com.pcz.reactive.spring.bucks.converter.LocalDateTimeToDateConverter;
import com.pcz.reactive.spring.bucks.converter.MoneyReadConverter;
import com.pcz.reactive.spring.bucks.converter.MoneyWriteConverter;
import com.pcz.reactive.spring.bucks.model.Coffee;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.connectionfactory.init.CompositeDatabasePopulator;
import org.springframework.data.r2dbc.connectionfactory.init.ConnectionFactoryInitializer;
import org.springframework.data.r2dbc.connectionfactory.init.ResourceDatabasePopulator;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Arrays;
import java.util.List;

/**
 * @author picongzhi
 */
@Configuration
public class R2dbcConfig extends AbstractR2dbcConfiguration {
    @Override
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get("r2dbc:h2:mem:testdb");
    }

    @Override
    protected List<Object> getCustomConverters() {
        return Arrays.asList(
                new MoneyReadConverter(),
                new MoneyWriteConverter(),
                new LocalDateTimeToDateConverter(),
                new DateToLocalDatetimeConverter());
    }

    @Bean
    public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);

        CompositeDatabasePopulator populator = new CompositeDatabasePopulator();
        populator.addPopulators(new ResourceDatabasePopulator(
                new ClassPathResource("schema.sql")));
        populator.addPopulators(new ResourceDatabasePopulator(
                new ClassPathResource("data.sql")));
        initializer.setDatabasePopulator(populator);

        return initializer;
    }

    @Bean
    public ReactiveRedisTemplate<String, Coffee> reactiveRedisTemplate(ReactiveRedisConnectionFactory connectionFactory) {
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<Coffee> valueSerializer = new Jackson2JsonRedisSerializer<>(Coffee.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String, Coffee> builder =
                RedisSerializationContext.newSerializationContext(keySerializer);
        RedisSerializationContext<String, Coffee> context = builder.value(valueSerializer).build();

        return new ReactiveRedisTemplate<>(connectionFactory, context);
    }
}
