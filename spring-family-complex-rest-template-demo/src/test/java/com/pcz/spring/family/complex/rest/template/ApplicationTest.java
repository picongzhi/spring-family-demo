package com.pcz.spring.family.complex.rest.template;

import com.pcz.spring.family.complex.rest.template.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
                .fromUriString("http://localhost:8080/coffee?name={name}")
                .build("mocha");
        RequestEntity<Void> requestEntity = RequestEntity.get(uri)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .build();
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        log.info("Response status: {}, headers: {}",
                responseEntity.getStatusCode(), responseEntity.getHeaders().toString());
        log.info("Coffee: {}", responseEntity.getBody());

        Coffee coffee = Coffee.builder()
                .name("Americano")
                .price(Money.of(CurrencyUnit.of("CNY"), 25.00))
                .build();
        Coffee response = restTemplate.postForObject("http://localhost:8080/coffee",
                coffee, Coffee.class);
        log.info("Response: {}", response);

        ParameterizedTypeReference<List<Coffee>> parameterizedTypeReference =
                new ParameterizedTypeReference<List<Coffee>>() {
                };
        ResponseEntity<List<Coffee>> coffees = restTemplate.exchange("http://localhost:8080/coffee",
                HttpMethod.GET, null, parameterizedTypeReference);
        log.info("Coffees: {}", coffees.getBody());
    }
}
