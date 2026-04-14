package com.sdn.health.appointment.model;

import com.sdn.health.common.code.constants.AppointmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "appointment")
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patientId;

    private Long doctorId;

    private ZonedDateTime startTime;

    private ZonedDateTime endTime;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

}
