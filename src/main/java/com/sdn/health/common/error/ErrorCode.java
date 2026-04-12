package com.sdn.health.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // General
    INTERNAL_SERVER_ERROR("E500", "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_REQUEST("E400", "Invalid request", HttpStatus.BAD_REQUEST),

    // Patient
    PATIENT_NOT_FOUND("P404", "Patient not found", HttpStatus.NOT_FOUND),
    PATIENT_ALREADY_EXISTS("P409", "Patient already exists", HttpStatus.CONFLICT),

    //Doctor
    DOCTOR_NOT_FOUND("D404","Doctor not found" ,HttpStatus.NOT_FOUND );

    private final String code;
    private final String message;
    private final HttpStatus status;

    public String getMessage(MessageSource messageSource) {
        return messageSource.getMessage(this.code, null, LocaleContextHolder.getLocale());
    }
}

