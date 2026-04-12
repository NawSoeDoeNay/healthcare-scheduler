package com.sdn.health.doctor.dto.response;

import com.sdn.health.common.code.constants.ExperienceLevel;
import com.sdn.health.doctor.model.Doctor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponseDto {

    private Long id;
    private String name;
    private String email;
    private String specialization;
    private ExperienceLevel experienceLevel;

    public static DoctorResponseDto fromEntity(Doctor doctor){
        return DoctorResponseDto.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .email(doctor.getEmail())
                .specialization(doctor.getSpecialization())
                .experienceLevel(doctor.getExperience())
                .build();
    }
}
