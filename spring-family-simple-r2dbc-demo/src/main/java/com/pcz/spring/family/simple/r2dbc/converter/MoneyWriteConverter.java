package com.pcz.spring.family.simple.r2dbc.converter;

import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;

/**
 * @author picongzhi
 */
public class MoneyWriteConverter implements Converter<Money, Long> {
    @Override
    public Long convert(Money money) {
        return money.getAmountMinorLong();
    }
}
