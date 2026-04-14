package com.sdn.health.appointment.dto.response;

import com.sdn.health.appointment.model.Appointment;
import com.sdn.health.common.code.constants.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentDto {

    private Long id;
    private Long patientId;
    private Long doctorId;
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;
    private AppointmentStatus status;

    public static AppointmentDto fromEntity(Appointment appointment){
        return AppointmentDto.builder()
                .id(appointment.getId())
                .doctorId(appointment.getDoctorId())
                .patientId(appointment.getPatientId())
                .startTime(appointment.getStartTime())
                .endTime(appointment.getEndTime())
                .status(appointment.getStatus())
                .build();
    }
}
