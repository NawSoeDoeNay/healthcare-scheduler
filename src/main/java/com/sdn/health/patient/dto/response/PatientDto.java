package com.sdn.health.patient.dto.response;

import com.sdn.health.patient.model.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientDto {

    private Long id;
    private String name;
    private String email;
    private LocalDate dob;

    public static PatientDto fromEntity(Patient patient){
        return PatientDto.builder()
                .id(patient.getId())
                .name(patient.getName())
                .email(patient.getEmail())
                .dob(patient.getDob())
                .build();
    }
}
