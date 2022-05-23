package com.skywalk.error.lib.config;

import com.skywalk.error.lib.filter.SkywalkErrorHandlingFilter;

import lombok.RequiredArgsConstructor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ErrorHandlingConfig {

    private final ObjectMapper objectMapper;

    @Bean
    public SkywalkErrorHandlingFilter errorHandlingFilter() {
        return new SkywalkErrorHandlingFilter(objectMapper);
    }

}
