package com.pcz.spring.family.context.hierarchy.bean;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author picongzhi
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class DemoBean {
    private String context;

    public void hello() {
        log.info("hello " + context);
    }
}
