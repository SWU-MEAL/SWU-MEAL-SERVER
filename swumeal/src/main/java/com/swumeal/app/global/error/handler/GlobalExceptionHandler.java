package com.swumeal.app.global.error.handler;

import com.swumeal.app.domain.menu.error.exception.DataUploadException;
import com.swumeal.app.domain.menu.error.exception.MenuApiException;
import com.swumeal.app.global.common.response.ResponseDto;
import com.swumeal.app.global.error.GlobalErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    // MenuApiException
    @ExceptionHandler(MenuApiException.class)
    public ResponseEntity<ResponseDto> exceptionHandler(MenuApiException e) {
        HttpStatus httpStatus = e.getErrorCode().getHttpStatus();
        String message = e.getMessage();

        e.printStackTrace();

        return ResponseEntity.status(httpStatus.value()).body(ResponseDto.of(httpStatus.value(), message));
    }

    // DataUploadException
    @ExceptionHandler(DataUploadException.class)
    public ResponseEntity<ResponseDto> exceptionHandler(DataUploadException e) {
        HttpStatus httpStatus = e.getErrorCode().getHttpStatus();
        String message = e.getMessage();

        e.printStackTrace();

        return ResponseEntity.status(httpStatus.value()).body(ResponseDto.of(httpStatus.value(), message));
    }

    // IOException
    @ExceptionHandler(IOException.class)
    public ResponseEntity<ResponseDto> exceptionHandler(IOException e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = httpStatus.getReasonPhrase();

        log.error(e.getMessage());
        e.printStackTrace();

        return ResponseEntity.internalServerError().body(ResponseDto.of(httpStatus.value(), message));
    }

    // MethodArgumentNotValidException
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        HttpStatus httpStatus = GlobalErrorCode.INVALID_VALUE.getHttpStatus();
        String message = e.getMessage();

        if (message.contains("date"))
            message = GlobalErrorCode.INVALID_DATE_FORMAT.getMessage();
        else if (message.contains("time"))
            message = GlobalErrorCode.INVALID_TIME_FORMAT.getMessage();

        e.printStackTrace();

        return ResponseEntity.status(httpStatus.value()).body(ResponseDto.of(httpStatus.value(), message));

    }

    // MethodNotAllowed
    @Override
    public ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
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
