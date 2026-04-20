package com.bookstore.customer.exception;

public class CustomerOperationException extends RuntimeException {
    public CustomerOperationException(String message) {
        super(message);
    }
}

