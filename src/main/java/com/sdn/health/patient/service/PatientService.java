package com.sdn.health.patient.service;

import com.sdn.health.patient.dto.PatientDto;
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
    public PatientDto createPatient(PatientDto dto){
        //Convert dto to entity
        Patient patient = Patient.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
        //Save to database
        patientRepository.save(patient);
        return PatientDto.fromEntity(patient);
    }

    @Transactional(readOnly = true)
    public List<PatientDto> getAllPatient(){
        return patientRepository.findAll()
                .stream()
                .map(PatientDto::fromEntity)
                .collect(Collectors.toList());

    }



}
