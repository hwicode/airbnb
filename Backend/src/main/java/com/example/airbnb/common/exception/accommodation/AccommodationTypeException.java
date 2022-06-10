package com.example.airbnb.common.exception.accommodation;

import com.example.airbnb.common.exception.BaseExceptionType;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum AccommodationTypeException implements BaseExceptionType {

    ACCOMMODATION_NOT_FOUND(404, "해당 숙소를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    RESERVATION_NOT_FOUND(404, "해당 숙소를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);

    private final int code;
    private final String message;
    private final HttpStatus status;

    AccommodationTypeException(int code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    @Override
    public int getErrorCode() {
        return code;
    }

    @Override
    public String getErrorMessage() {
        return message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return status;
    }
}
