package com.example.airbnb.common.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private BaseExceptionType baseExceptionType;

    public BusinessException(BaseExceptionType baseExceptionType) {
        this.baseExceptionType = baseExceptionType;
    }
}
