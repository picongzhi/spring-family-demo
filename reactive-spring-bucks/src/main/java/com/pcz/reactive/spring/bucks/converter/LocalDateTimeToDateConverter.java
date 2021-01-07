package com.pcz.reactive.spring.bucks.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author picongzhi
 */
public class LocalDateTimeToDateConverter implements Converter<LocalDateTime, Date> {
    @Override
    public Date convert(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
