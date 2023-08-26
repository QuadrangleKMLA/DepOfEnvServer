package com.quadrangle.depOfEnv.exception.advice;

import com.quadrangle.depOfEnv.exception.ErrorMessage;
import com.quadrangle.depOfEnv.exception.exception.TokenRefreshException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class TokenRefreshControllerAdvice {

    @ExceptionHandler(TokenRefreshException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessage tokenRefreshException(TokenRefreshException e, WebRequest request) {
        return new ErrorMessage(HttpStatus.FORBIDDEN.value(), new Date(), e.getMessage(), request.getDescription(false));
    }
}
