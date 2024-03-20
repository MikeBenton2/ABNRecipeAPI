package com.abn.recipeapi_v1.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public record APIException(String message, Throwable throwable, HttpStatus status, ZonedDateTime timestamp) {

}
