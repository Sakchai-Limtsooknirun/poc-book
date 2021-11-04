package com.example.demo;

import feign.FeignException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public String handleFeignStatusException(FeignException e, HttpServletResponse response) {
        response.setStatus(e.status());
        return "feignError";
    }

    @ExceptionHandler(Exception.class)
    public String handleExceptionStatusException(Exception e, HttpServletResponse response) {
        response.setStatus(500);
        return "UnknownError";
    }

}
