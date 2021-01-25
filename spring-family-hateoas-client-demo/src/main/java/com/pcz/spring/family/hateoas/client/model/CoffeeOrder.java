package com.pcz.spring.family.hateoas.client.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author picongzhi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class CoffeeOrder implements Serializable {
    private Long id;

    private String customer;

    private OrderState state;

    private Date createTime;

    private Date updateTime;
}
