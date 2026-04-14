package com.sdn.health.appointment.repo;

import com.sdn.health.appointment.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsByDoctorIdAndStartTimeLessThanAndEndTimeGreaterThan(
            Long doctorId,
            ZonedDateTime endTime,
            ZonedDateTime startTime
    );

    boolean existsByPatientIdAndStartTimeLessThanAndEndTimeGreaterThan(
            Long patientId,
            ZonedDateTime endTime,
            ZonedDateTime startTime
    );
}
