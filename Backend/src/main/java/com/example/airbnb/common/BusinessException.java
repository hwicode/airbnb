package com.example.airbnb.common;

import com.example.airbnb.common.exception.BaseExceptionType;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private BaseExceptionType baseExceptionType;

    public BusinessException(BaseExceptionType baseExceptionType) {
        this.baseExceptionType = baseExceptionType;
    }
}
