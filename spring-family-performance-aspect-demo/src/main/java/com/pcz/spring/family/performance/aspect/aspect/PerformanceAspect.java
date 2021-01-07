package com.pcz.spring.family.performance.aspect.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author picongzhi
 */
@Slf4j
@Aspect
@Component
public class PerformanceAspect {
    @Pointcut("execution(* com.pcz.spring.family.performance.aspect.repository..*(..))")
    private void repositoryOps() {
    }

    @Around("repositoryOps()")
    public Object logPerformance(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        String name = proceedingJoinPoint.getSignature().toShortString();

        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable t) {
            log.error("{}:{}:{}ms", name, "FAILED", System.currentTimeMillis() - startTime);
            throw t;
        } finally {
            log.info("{}:{}:{}ms", name, "SUCCESS", System.currentTimeMillis() - startTime);
        }
    }
}
