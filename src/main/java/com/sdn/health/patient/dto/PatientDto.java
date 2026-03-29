package com.sdn.health.patient.dto;

import com.sdn.health.patient.model.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientDto {

    private String name;
    private String email;

    public static PatientDto fromEntity(Patient patient){
        return PatientDto.builder()
                .name(patient.getName())
                .email(patient.getEmail())
                .build();
    }
}
