package com.pcz.spring.family.context.hierarchy;

import com.pcz.spring.family.context.hierarchy.bean.DemoBean;
import com.pcz.spring.family.context.hierarchy.config.ContextConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
    @Test
    public void contextLoads() {
    }

    @Test
    public void hierarchyContextTest() {
        ApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(ContextConfig.class);
        ClassPathXmlApplicationContext classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"},
                        annotationConfigApplicationContext);

        DemoBean demoBean = annotationConfigApplicationContext.getBean("demoBean1", DemoBean.class);
        demoBean.hello();

        demoBean = classPathXmlApplicationContext.getBean("demoBean1", DemoBean.class);
        demoBean.hello();

        demoBean = classPathXmlApplicationContext.getBean("demoBean2", DemoBean.class);
        demoBean.hello();
    }
}
