package com.skywalk.error.lib.exception;

import lombok.Getter;

import java.util.List;

import com.skywalk.error.lib.exception.resource.ErrorResource;
import com.skywalk.error.lib.exception.resource.FiledErrorResource;

@Getter
public abstract class SkywalkException extends RuntimeException {

    private final String message;
    private final String detailedMessage;
    private final List<FiledErrorResource> filedErrors;
    private final int httpStatus;
    private final Throwable cause;

    public SkywalkException(String message, String detailedMessage,
            List<FiledErrorResource> filedErrors, int httpStatus, Throwable cause) {
        super(message, cause);

        this.message = message;
        this.detailedMessage = detailedMessage;
        this.filedErrors = filedErrors;
        this.httpStatus = httpStatus;
        this.cause = cause;
    }

    public ErrorResource toErrorResource() {
        return ErrorResource.builder()
                .filedErrors(getFiledErrors())
                .message(getMessage())
                .build();
    }

}
