package com.example.qkare.CustomerOrders.Exception;

import org.springframework.http.HttpStatus;

public class Exceptions extends BaseException {
    public Exceptions(MessageKey messageKey, Object... args) {
        super(messageKey, HttpStatus.NOT_FOUND, args);
    }
}
