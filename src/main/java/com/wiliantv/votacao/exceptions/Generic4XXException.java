package com.wiliantv.votacao.exceptions;

public class Generic4XXException extends RuntimeException{

    public Generic4XXException() {
    }

    public Generic4XXException(String message) {
        super(message);
    }

    public Generic4XXException(String message, Throwable cause) {
        super(message, cause);
    }

    public Generic4XXException(Throwable cause) {
        super(cause);
    }

    public Generic4XXException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
