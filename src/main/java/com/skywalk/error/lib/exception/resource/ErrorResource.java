package com.skywalk.error.lib.exception.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@Value
@Builder(toBuilder = true)
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResource {

    String message;
    List<FiledErrorResource> filedErrors;

}
