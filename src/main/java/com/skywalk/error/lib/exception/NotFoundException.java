package com.skywalk.error.lib.exception;

public class NotFoundException extends SkywalkException {

    public NotFoundException(String message, String detailedMessage) {
        super(message, detailedMessage, null, 404, null);
    }

}
