package com.pcz.spring.family.r2dbc.repository.converter;

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
