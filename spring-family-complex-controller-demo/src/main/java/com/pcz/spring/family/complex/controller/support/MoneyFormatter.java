package com.pcz.spring.family.complex.controller.support;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

/**
 * @author picongzhi
 */
@Component
public class MoneyFormatter implements Formatter<Money> {
    @Override
    public Money parse(String text, Locale locale) throws ParseException {
        if (NumberUtils.isParsable(text)) {
            return Money.of(CurrencyUnit.of("CNY"), NumberUtils.createBigDecimal(text));
        } else if (StringUtils.isNotEmpty(text)) {
            String[] arrs = StringUtils.split(text, " ");
            if (arrs != null && arrs.length == 2 && NumberUtils.isParsable(arrs[1])) {
                return Money.of(CurrencyUnit.of(arrs[0]), NumberUtils.createBigDecimal(arrs[1]));
            } else {
                throw new ParseException(text, 0);
            }
        }

        throw new ParseException(text, 0);
    }

    @Override
    public String print(Money money, Locale locale) {
        return money == null ?
                null :
                money.getCurrencyUnit().getCode() + " " + money.getAmount();
    }
}
