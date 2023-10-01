package com.swumeal.app.global.error.handler;

import com.swumeal.app.domain.menu.error.exception.DataUploadException;
import com.swumeal.app.global.common.response.ResponseDto;
import com.swumeal.app.global.error.GlobalErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    // DataUploadException
    @ExceptionHandler(DataUploadException.class)
    public ResponseEntity<ResponseDto> exceptionHandler(DataUploadException e) {
        HttpStatus httpStatus = e.getErrorCode().getHttpStatus();
        String message = e.getMessage();

        e.printStackTrace();

        return ResponseEntity.status(httpStatus.value()).body(ResponseDto.of(httpStatus.value(), message));
    }

    // IOException
    @ExceptionHandler(value = IOException.class)
    public ResponseEntity<ResponseDto> exceptionHandler(IOException e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = httpStatus.getReasonPhrase();

        log.error(e.getMessage());
        e.printStackTrace();

        return ResponseEntity.internalServerError().body(ResponseDto.of(httpStatus.value(), message));
    }

    // MethodNotAllowed
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResponseDto> exceptionHandler(HttpRequestMethodNotSupportedException e) {
        HttpStatus httpStatus = GlobalErrorCode.METHOD_NOT_ALLOWED.getHttpStatus();
        String message = e.getMessage();

        e.printStackTrace();

        return ResponseEntity.status(httpStatus.value()).body(ResponseDto.of(httpStatus.value(), message));
    }

    // InternalServerError
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> exceptionHandler(Exception e) {
        HttpStatus httpStatus = GlobalErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus();
        String message = httpStatus.getReasonPhrase();

        e.printStackTrace();

        return ResponseEntity.status(httpStatus.value()).body(ResponseDto.of(httpStatus.value(), message));
    }
}
