package com.keshavbansal.ECommerceStore.globalException;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }
}
