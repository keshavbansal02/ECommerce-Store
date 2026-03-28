package com.keshavbansal.ECommerceStore.globalException;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}
