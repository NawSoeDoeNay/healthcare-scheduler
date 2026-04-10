package com.sdn.health.common.error;

import lombok.Builder;
import lombok.Getter;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
@Builder
public class ErrorResponse {

    private  String errorCode;
    private  String errorMessage;
    private  int status;

    private static MessageSource messageSource;

    public static ErrorResponse of(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .errorCode(errorCode.getCode())
                .errorMessage(errorCode.getMessage(messageSource))
                .status(errorCode.getStatus().value())
                .build();
    }

    public static ErrorResponse of( String errorCode, String errorMessage, HttpStatus status) {
        return ErrorResponse.builder()
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .status(status.value())
                .build();
    }

    public static ErrorResponse of(final String errorCode, final BindingResult bindingResult) {
        return ErrorResponse.builder()
                .errorCode(errorCode)
                .errorMessage(createErrorMesage(bindingResult))
                .build();
    }

    private static String createErrorMesage(final BindingResult bindingResult) {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            if (!isFirst) {
                sb.append(", ");
            } else {
                isFirst = false;
            }

            sb.append("[");
            sb.append(fieldError.getField());
            sb.append("]");
            sb.append(fieldError.getDefaultMessage());
        }

        return sb.toString();
    }
}
