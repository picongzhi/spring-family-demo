package com.pcz.spring.family.session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author picongzhi
 */
@SpringBootApplication
@RestController
@EnableRedisHttpSession
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping("/hello")
    public String hello(HttpSession httpSession, String name) {
        String value = (String) httpSession.getAttribute("name");
        if (value == null) {
            httpSession.setAttribute("name", name);
            value = name;
        }

        return "hello " + value;
    }
}
