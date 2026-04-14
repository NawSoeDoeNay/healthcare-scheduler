package com.sdn.health.appointment.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class AppointmentReqDto {

    @NotNull
    private Long patientId;

    @NotNull
    private Long doctorId;

    @NotNull
    private ZonedDateTime startTime;

    @NotNull
    private ZonedDateTime endTime;
}
