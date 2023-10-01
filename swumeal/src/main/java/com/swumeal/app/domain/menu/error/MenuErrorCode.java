package com.swumeal.app.domain.menu.error;

import com.swumeal.app.global.error.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum MenuErrorCode implements ErrorCode {
    INVALID_FILE_EXTENSION(HttpStatus.BAD_REQUEST, "File extension not found."),
    INVALID_FILE_FORMAT(HttpStatus.BAD_REQUEST, "Invalid file format - .xlsx, .xls only"),
    INVALID_TIME_FORMAT(HttpStatus.BAD_REQUEST, "Invalid time format."),
    INVALID_DATE_FORMAT(HttpStatus.BAD_REQUEST, "Invalid date format. (Date format - YY-MM-DD)");

    private final HttpStatus httpStatus;
    private final String message;

    MenuErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
