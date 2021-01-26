package com.pcz.spring.family.webflux.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author picongzhi
 */
@Table("T_ORDER")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class CoffeeOrder implements Serializable {
    @Id
    private Long id;

    private String customer;

    private List<Coffee> items;

    private OrderState state;

    private Date createTime;

    private Date updateTime;
}
