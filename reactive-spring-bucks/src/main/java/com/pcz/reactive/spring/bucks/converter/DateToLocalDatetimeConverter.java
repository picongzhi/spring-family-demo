package com.pcz.reactive.spring.bucks.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author picongzhi
 */
public class DateToLocalDatetimeConverter implements Converter<Date, LocalDateTime> {
    @Override
    public LocalDateTime convert(Date date) {
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
