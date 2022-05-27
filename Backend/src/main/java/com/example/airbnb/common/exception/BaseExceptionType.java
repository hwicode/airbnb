package com.example.airbnb.common.exception;

import org.springframework.http.HttpStatus;

public interface BaseExceptionType {
    int getErrorCode();

    String getMessage();

    HttpStatus getHttpStatus();

}
