package com.swumeal.app.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum GlobalErrorCode implements ErrorCode {
    VALUE_REQUIRED(HttpStatus.BAD_REQUEST, "Value required."),
    INVALID_VALUE(HttpStatus.BAD_REQUEST, "Invalid value."),
    INVALID_DATE_FORMAT(HttpStatus.BAD_REQUEST, "Invalid date format.\n<Valid: YYYY-MM-DD>"),
    INVALID_TIME_FORMAT(HttpStatus.BAD_REQUEST, "Invalid time format.\n<Valid: b(breakfast) / l(lunch) / d(dinner)>"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "Request method is not supported."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error.");

    private final HttpStatus httpStatus;
    private final String message;

    GlobalErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
