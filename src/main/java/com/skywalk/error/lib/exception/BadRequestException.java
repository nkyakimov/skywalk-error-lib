package com.skywalk.error.lib.exception;

import java.util.List;

import com.skywalk.error.lib.exception.resource.FiledErrorResource;

public class BadRequestException extends SkywalkException {

    public BadRequestException(Throwable cause) {
        super("A Bad Request Occurred", "A Bad Request Occurred", null, 400, cause);
    }

    public BadRequestException(String message, String detailedMessage) {
        super(message, detailedMessage, null, 400, null);
    }

    public BadRequestException(String message, String detailedMessage, List<FiledErrorResource> filedErrors,
            Throwable cause) {
        super(message, detailedMessage, filedErrors, 400, cause);
    }

}
