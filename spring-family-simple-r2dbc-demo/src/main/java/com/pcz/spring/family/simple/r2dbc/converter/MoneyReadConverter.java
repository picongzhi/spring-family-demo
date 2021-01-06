package com.pcz.spring.family.simple.r2dbc.converter;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;

/**
 * @author picongzhi
 */
public class MoneyReadConverter implements Converter<Long, Money> {
    @Override
    public Money convert(Long aLong) {
        return Money.ofMinor(CurrencyUnit.of("CNY"), aLong);
    }
}
