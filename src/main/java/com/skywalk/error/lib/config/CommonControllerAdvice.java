package com.skywalk.error.lib.config;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.skywalk.error.lib.exception.resource.ErrorResource;
import com.skywalk.error.lib.exception.resource.FiledErrorResource;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CommonControllerAdvice {

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResource handleException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();

        return processFieldErrors(fieldErrors);
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorResource handleException(HttpMessageNotReadableException ex) {
        return ErrorResource.builder().message("Request not in correct format").build();
    }

    private ErrorResource processFieldErrors(List<org.springframework.validation.FieldError> fieldErrors) {
        List<FiledErrorResource> filedErrorResources = fieldErrors.stream()
                .map(fe -> new FiledErrorResource(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());

        return new ErrorResource("Request contains errors", filedErrorResources);
    }

}