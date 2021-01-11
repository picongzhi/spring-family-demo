package com.pcz.spring.family.simple.controller.controller.request;

import lombok.Data;

import java.util.List;

/**
 * @author picongzhi
 */
@Data
public class NewOrderRequest {
    private String customer;
    private List<String> items;
}
