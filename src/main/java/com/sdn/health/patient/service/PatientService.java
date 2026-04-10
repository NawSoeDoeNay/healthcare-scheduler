package com.sdn.health.patient.service;

import com.sdn.health.common.error.CustomException;
import com.sdn.health.common.error.ErrorCode;
import com.sdn.health.patient.dto.response.PatientDto;
import com.sdn.health.patient.dto.request.PatientReqDto;
import com.sdn.health.patient.model.Patient;
import com.sdn.health.patient.repo.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    @Transactional
    public PatientDto createPatient(PatientReqDto dto){
        //Convert dto to entity
        Patient patient = Patient.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .dob(dto.getDob())
                .build();
        //Save to database
        patientRepository.save(patient);
        return PatientDto.fromEntity(patient);
    }

    @Transactional(readOnly = true)
    public List<PatientDto> getAllPatients(){
        return patientRepository.findAll()
                .stream()
                .map(PatientDto::fromEntity)
                .collect(Collectors.toList());

    }
    @Transactional(readOnly = true)
    public PatientDto getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.PATIENT_NOT_FOUND));
        return PatientDto.fromEntity(patient);
    }

    @Transactional
    public PatientDto updatePatient(Long id, PatientReqDto dto) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.PATIENT_NOT_FOUND));

        patient.setName(dto.getName());
        patient.setEmail(dto.getEmail());
        patient.setDob(dto.getDob()); // IMPORTANT

        Patient updated = patientRepository.save(patient);

        return PatientDto.fromEntity(updated);
    }

    @Transactional
    public void deletePatient(Long id) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.PATIENT_NOT_FOUND));

        patientRepository.delete(patient);
    }

}
