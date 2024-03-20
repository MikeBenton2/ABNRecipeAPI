package com.abn.recipeapi_v1.exception;

public class APIRequestException extends RuntimeException {

    public APIRequestException(String message) {
        super(message);
    }
}
