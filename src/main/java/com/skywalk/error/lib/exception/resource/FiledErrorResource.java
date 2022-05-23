package com.skywalk.error.lib.exception.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@AllArgsConstructor
public class FiledErrorResource {

    String field;
    String message;

}
