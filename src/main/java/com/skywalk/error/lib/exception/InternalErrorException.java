package com.skywalk.error.lib.exception;

public class InternalErrorException extends SkywalkException {

    public InternalErrorException(Throwable cause) {
        super("An Internal error occurred", "An Internal error occurred", null, 500, cause);
    }

}
