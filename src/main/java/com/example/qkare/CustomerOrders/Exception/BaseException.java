package com.example.qkare.CustomerOrders.Exception;

import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException {
    private final MessageKey messageKey;
    private final Object[] args;
    private final HttpStatus httpStatus;

    protected BaseException(MessageKey messageKey, HttpStatus httpStatus, Object... args) {
        this.messageKey = messageKey;
        this.args = args;
        this.httpStatus = httpStatus;
    }

    public MessageKey getMessageKey() {
        return messageKey;
    }

    public Object[] getArgs() {
        return args;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
