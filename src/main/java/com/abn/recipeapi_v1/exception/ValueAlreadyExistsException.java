package com.abn.recipeapi_v1.exception;

public class ValueAlreadyExistsException extends RuntimeException{
    public ValueAlreadyExistsException() {
        super();
    }

    public ValueAlreadyExistsException(String message) {
        super(message);
    }
}