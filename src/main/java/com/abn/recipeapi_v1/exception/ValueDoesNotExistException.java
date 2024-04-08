package com.abn.recipeapi_v1.exception;

public class ValueDoesNotExistException extends RuntimeException {
    public ValueDoesNotExistException() {
        super();
    }

    public ValueDoesNotExistException(String message) {
        super(message);
    }
}
