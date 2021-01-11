package com.pcz.spring.family.context.hierarchy.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author picongzhi
 */
@Slf4j
@Aspect
public class PrintAspect {
    @AfterReturning("bean(demoBean*)")
    public void printAfter() {
        log.info("After hello()");
    }
}
