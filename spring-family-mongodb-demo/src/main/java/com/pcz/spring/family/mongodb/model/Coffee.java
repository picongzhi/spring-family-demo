package com.pcz.spring.family.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author picongzhi
 */
@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {
    @Id
    private String id;

    private String name;

    private Money price;

    private Date createTime;

    private Date updateTime;
}
