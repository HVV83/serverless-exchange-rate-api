package com.vkhoma.aws.exception;

public class NoDataReceivedException extends RuntimeException {

    public NoDataReceivedException(final String message) {
        super(message);
    }

    public NoDataReceivedException(final String message, final Throwable cause) {
        super(message, cause);
    }

}