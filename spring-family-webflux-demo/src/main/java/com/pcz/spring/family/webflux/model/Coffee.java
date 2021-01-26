package com.pcz.spring.family.webflux.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pcz.spring.family.webflux.serializer.MoneyDeserializer;
import com.pcz.spring.family.webflux.serializer.MoneySerializer;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * @author picongzhi
 */
@Table("T_COFFEE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
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
