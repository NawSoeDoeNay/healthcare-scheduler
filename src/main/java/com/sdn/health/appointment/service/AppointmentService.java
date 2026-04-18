package com.sdn.health.appointment.service;

import com.sdn.health.appointment.dto.request.AppointmentReqDto;
import com.sdn.health.appointment.dto.response.AppointmentDto;
import com.sdn.health.appointment.model.Appointment;
import com.sdn.health.appointment.repo.AppointmentRepository;
import com.sdn.health.common.code.constants.AppointmentStatus;
import com.sdn.health.common.error.CustomException;
import com.sdn.health.common.error.ErrorCode;
import com.sdn.health.doctor.repo.DoctorRepository;
import com.sdn.health.patient.repo.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    
    @Transactional
    public AppointmentDto bookAppointment(AppointmentReqDto dto){

        doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new CustomException(ErrorCode.DOCTOR_NOT_FOUND));

        patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new CustomException(ErrorCode.PATIENT_NOT_FOUND));

        if(dto.getStartTime().isAfter(dto.getEndTime())){
            throw new CustomException(ErrorCode.INVALID_REQUEST);
        }

        if(dto.getStartTime().isBefore(ZonedDateTime.now())){
            throw new CustomException(ErrorCode.INVALID_REQUEST);
        }

        boolean doctorConflict =
                appointmentRepository.existsByDoctorIdAndStartTimeLessThanAndEndTimeGreaterThan(
                        dto.getDoctorId(),
                        dto.getEndTime(),
                        dto.getStartTime()
                );

        if(doctorConflict){
            throw new CustomException(ErrorCode.APPOINTMENT_CONFLICT);
        }

        boolean patientConflict =
                appointmentRepository.existsByPatientIdAndStartTimeLessThanAndEndTimeGreaterThan(
                        dto.getPatientId(),
                        dto.getEndTime(),
                        dto.getStartTime()
                );

        if(patientConflict){
            throw new CustomException(ErrorCode.APPOINTMENT_CONFLICT);
        }

        Appointment appointment = Appointment.builder()
                .patientId(dto.getPatientId())
                .doctorId(dto.getDoctorId())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .status(AppointmentStatus.BOOKED)
                .build();

        Appointment saved = appointmentRepository.save(appointment);

        return AppointmentDto.fromEntity(saved);
    }

    @Transactional
    public void cancelAppointment(Long id){
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.INVALID_REQUEST)
        );
        appointment.setStatus(AppointmentStatus.CANCELLED);
        appointmentRepository.save(appointment);
    }

    public List<AppointmentDto> getAppointmentsByDoctor(Long doctorId){
        return appointmentRepository.findAll()
                .stream()
                .filter(a -> a.getDoctorId().equals(doctorId))
                .map(AppointmentDto::fromEntity)
                .toList();
    }

    public List<AppointmentDto> getAppointmentsByPatient(Long patientId) {
        return appointmentRepository.findAll()
                .stream()
                .filter(a -> a.getPatientId().equals(patientId))
                .map(AppointmentDto::fromEntity)
                .toList();
    }
}
