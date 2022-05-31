package com.example.airbnb.common.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {

    private static final int SERVER_ERROR_CODE = 500;
    private static final String SERVER_ERROR_MESSAGE = "서버 내부 오류입니다.";

    private int code;
    private String message;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime time;

    public ErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;
        this.time = LocalDateTime.now();
    }

    public ErrorResponse() {
    }

    static ErrorResponse create(BaseExceptionType baseExceptionType) {
        return new ErrorResponse(baseExceptionType.getErrorCode(), baseExceptionType.getErrorMessage());
    }

    static ErrorResponse unResolved(Exception exception) {
        return new ErrorResponse(SERVER_ERROR_CODE, SERVER_ERROR_MESSAGE);
    }
}

