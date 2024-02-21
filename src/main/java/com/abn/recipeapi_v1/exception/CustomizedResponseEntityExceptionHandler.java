package com.abn.recipeapi_v1.exception;

import com.abn.recipeapi_v1.model.Error;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, @Nullable HttpHeaders headers, HttpStatusCode status, @Nullable WebRequest request) {
        Error error = new Error(status.value(), ex.getLocalizedMessage());
        return new ResponseEntity<>(error, status);

//        return super.handleMissingServletRequestParameter(error, headers, status, request);
    }
}
