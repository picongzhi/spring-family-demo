package com.pcz.spring.family.context.hierarchy.config;

import com.pcz.spring.family.context.hierarchy.aspect.PrintAspect;
import com.pcz.spring.family.context.hierarchy.bean.DemoBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author picongzhi
 */
@Configuration
@EnableAspectJAutoProxy
public class ContextConfig {
    @Bean
    public DemoBean demoBean1() {
        return new DemoBean("parent");
    }

    @Bean
    public DemoBean demoBean2() {
        return new DemoBean("parent");
    }

    @Bean
    public PrintAspect printAspect() {
        return new PrintAspect();
    }
}
