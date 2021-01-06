package com.pcz.spring.family.r2dbc.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

/**
 * @author picongzhi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("t_coffee")
public class Coffee {
    @Id
    private Long id;

    private String name;

    private Money price;

    private Date createTime;

    private Date updateTime;
}
