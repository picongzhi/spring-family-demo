package com.pcz.spring.family.exception.controller;

import com.pcz.spring.family.exception.controller.exception.FormValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.xml.bind.ValidationException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author picongzhi
 */
@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(FormValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> validationExceptionHandler(FormValidationException exception) {
        Map<String, String> map = new HashMap<>();
        map.put("message", exception.getMessage());

        return map;
    }
}
