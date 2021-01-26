package com.pcz.spring.family.webflux.config;

import com.pcz.spring.family.webflux.support.DateToLocalDatetimeConverter;
import com.pcz.spring.family.webflux.support.LocalDateTimeToDateConverter;
import com.pcz.spring.family.webflux.support.MoneyReadConverter;
import com.pcz.spring.family.webflux.support.MoneyWriteConverter;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.connectionfactory.init.CompositeDatabasePopulator;
import org.springframework.data.r2dbc.connectionfactory.init.ConnectionFactoryInitializer;
import org.springframework.data.r2dbc.connectionfactory.init.ResourceDatabasePopulator;

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
                new DateToLocalDatetimeConverter()
        );
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
}
