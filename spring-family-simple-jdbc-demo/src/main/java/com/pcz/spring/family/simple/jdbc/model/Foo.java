package com.pcz.spring.family.simple.jdbc.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author picongzhi
 */
@Data
@Builder
public class Foo {
    private Long id;
    private String bar;
}
