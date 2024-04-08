package com.abn.recipeapi_v1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(value = APIRequestException.class)
    public ResponseEntity<Object> handleAPIRequestException(APIRequestException e) {
        //        log.error(BAD_REQUEST, e);
        APIException apiException = new APIException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ValueAlreadyExistsException.class)
    public ResponseEntity<Object> handleValueAlreadyExistsException(ValueAlreadyExistsException e) {
//        log.error(ALREADY_EXISTS, e);
        APIException apiException = new APIException(
                e.getMessage(),
                HttpStatus.CONFLICT,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(apiException, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = ValueDoesNotExistException.class)
    public ResponseEntity<Object> handleValueDoesNotExistException(ValueDoesNotExistException e) {
//        log.error(Does not exist, e);
        APIException apiException = new APIException(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }
}
