package com.sdn.health.doctor.service;

import com.sdn.health.common.error.CustomException;
import com.sdn.health.common.error.ErrorCode;
import com.sdn.health.doctor.dto.request.DoctorRequestDto;
import com.sdn.health.doctor.dto.response.DoctorResponseDto;
import com.sdn.health.doctor.model.Doctor;
import com.sdn.health.doctor.repo.DoctorJpaRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.Doc;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorJpaRepository doctorJpaRepository;

    private Doctor mapToEntity(DoctorRequestDto dto) {
        Doctor doctor = new Doctor();
        doctor.setName(dto.getName());
        doctor.setEmail(dto.getEmail());
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setExperience(dto.getLevel());
        return doctor;
    }

    @Transactional
    public DoctorResponseDto createDoctor(DoctorRequestDto dto){
        Doctor doctor = mapToEntity(dto);
        doctorJpaRepository.save(doctor);
        return DoctorResponseDto.fromEntity(doctor);
    }

    @Transactional(readOnly = true)
    public List<DoctorResponseDto> getAllDoctors(){
        return doctorJpaRepository.findAll()
                .stream()
                .map(DoctorResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<DoctorResponseDto> getDoctorByName(String name){
        List<Doctor> doctors = doctorJpaRepository.findByNameContainingIgnoreCase(name)
                .orElseThrow(() -> new CustomException(ErrorCode.DOCTOR_NOT_FOUND));
        return doctors.stream()
                .map(DoctorResponseDto::fromEntity)
                .toList();
    }

    @Transactional
    public DoctorResponseDto updateDoctor(Long id, DoctorRequestDto dto) {

        Doctor doctor = doctorJpaRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.DOCTOR_NOT_FOUND));

        doctor.setName(dto.getName());
        doctor.setEmail(dto.getEmail());
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setExperience(dto.getLevel());

        doctorJpaRepository.save(doctor);
        return DoctorResponseDto.fromEntity(doctor);
    }

    @Transactional
    public void deleteDoctor(Long id) {

        Doctor doctor = doctorJpaRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.DOCTOR_NOT_FOUND));

        doctorJpaRepository.delete(doctor);
    }

    @Transactional(readOnly = true)
    public  DoctorResponseDto getDoctorById(Long id) {
        Doctor doctor = doctorJpaRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.DOCTOR_NOT_FOUND));
        return DoctorResponseDto.fromEntity(doctor);
    }
}
