package com.pcz.spring.family.jpa.complex.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * @author picongzhi
 */
@Entity
@Table(name = "T_ORDER")
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeOrder extends BaseEntity {
    private String customer;

    @ManyToMany
    @JoinTable(name = "T_ORDER_COFFEE")
    @OrderBy("id")
    private List<Coffee> items;

    @Enumerated
    @Column(nullable = false)
    private OrderState state;
}
