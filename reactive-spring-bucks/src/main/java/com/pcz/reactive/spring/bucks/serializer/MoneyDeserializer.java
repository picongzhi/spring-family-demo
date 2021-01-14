package com.pcz.reactive.spring.bucks.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.io.IOException;

/**
 * @author picongzhi
 */
public class MoneyDeserializer extends StdDeserializer<Money> {
    protected MoneyDeserializer() {
        super(Money.class);
    }

    @Override
    public Money deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        return Money.ofMinor(CurrencyUnit.of("CNY"), jsonParser.getLongValue());
    }
}