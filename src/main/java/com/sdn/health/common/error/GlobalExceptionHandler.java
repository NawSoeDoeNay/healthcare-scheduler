package com.sdn.health.common.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    //  Handle custom exceptions
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {

        ErrorCode errorCode = ex.getErrorCode();

        ErrorResponse response = new ErrorResponse(
                errorCode.getCode(),
                errorCode.getMessage(),
                errorCode.getStatus().value()
        );

        return ResponseEntity.badRequest().body(response);
    }

    //  Handle validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage())
                );

        ErrorResponse response = new ErrorResponse(
                ErrorCode.INVALID_REQUEST.getCode(),
                "Validation failed",
                ErrorCode.INVALID_REQUEST.getStatus().value()
        );

        return ResponseEntity.badRequest().body(response);
    }

    //  Handle all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {

        ErrorResponse response = new ErrorResponse(
                ErrorCode.INTERNAL_SERVER_ERROR.getCode(),
                ex.getMessage(),
                ErrorCode.INTERNAL_SERVER_ERROR.getStatus().value()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
