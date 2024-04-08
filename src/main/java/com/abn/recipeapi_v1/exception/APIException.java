package com.abn.recipeapi_v1.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public record APIException(String message, HttpStatus status, ZonedDateTime timestamp) {

}
