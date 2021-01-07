package com.pcz.reactive.spring.bucks.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pcz.reactive.spring.bucks.serializer.MoneyDeserializer;
import com.pcz.reactive.spring.bucks.serializer.MoneySerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * @author picongzhi
 */
@Table("t_coffee")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coffee implements Serializable {
    @Id
    private Long id;

    private String name;

    @JsonSerialize(using = MoneySerializer.class)
    @JsonDeserialize(using = MoneyDeserializer.class)
    private Money price;

    private Date createTime;

    private Date updateTime;
}
