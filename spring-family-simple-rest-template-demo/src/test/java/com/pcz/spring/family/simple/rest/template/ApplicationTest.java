package com.pcz.spring.family.simple.rest.template;

import com.pcz.spring.family.simple.rest.template.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void restTemplateTest() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080/coffee/{id}")
                .build(1);
        ResponseEntity<Coffee> responseEntity = restTemplate.getForEntity(uri, Coffee.class);
        log.info("Status: {}, response header: {}",
                responseEntity.getStatusCode(), responseEntity.getHeaders().toString());
        log.info("Coffee: {}", responseEntity.getBody());

        Coffee coffee = Coffee.builder()
                .name("Americano")
                .price(BigDecimal.valueOf(25.00))
                .build();
        Coffee response = restTemplate.postForObject("http://localhost:8080/coffee",
                coffee, Coffee.class);
        log.info("res: {}", response);

        String res = restTemplate.getForObject("http://localhost:8080/coffee", String.class);
        log.info("res: {}", res);
    }
}
