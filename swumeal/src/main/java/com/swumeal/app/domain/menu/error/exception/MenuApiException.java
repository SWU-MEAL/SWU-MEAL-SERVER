package com.swumeal.app.domain.menu.error.exception;

import com.swumeal.app.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class MenuApiException extends RuntimeException {
    private final ErrorCode errorCode;

    public MenuApiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
