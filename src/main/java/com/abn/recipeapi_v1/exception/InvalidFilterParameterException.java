package com.abn.recipeapi_v1.exception;

public class InvalidFilterParameterException extends RuntimeException {
    public InvalidFilterParameterException(String message) {
        super(message);
    }
}
