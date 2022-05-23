package com.skywalk.error.lib.exception;

public abstract class AuthenticationException extends SkywalkException {

    public AuthenticationException(String message, String detailedMessage, int httpStatus) {
        super(message, detailedMessage, null, httpStatus, null);
    }

}
