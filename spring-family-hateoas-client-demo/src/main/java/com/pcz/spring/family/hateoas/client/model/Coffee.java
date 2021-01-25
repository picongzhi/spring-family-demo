package com.pcz.spring.family.hateoas.client.model;

import lombok.*;
import org.joda.money.Money;

import java.io.Serializable;
import java.util.Date;

/**
 * @author picongzhi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Coffee implements Serializable {
    private String name;

    private Money price;

    private Date createTime;

    private Date updateTime;
}
