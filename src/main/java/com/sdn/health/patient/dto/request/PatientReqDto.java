package com.sdn.health.patient.dto.request;

import com.sdn.health.patient.model.Patient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientReqDto {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotNull
    private LocalDate dob;

    public static PatientReqDto fromEntity(Patient patient){
        return PatientReqDto.builder()
                .name(patient.getName())
                .email(patient.getEmail())
                .dob(patient.getDob())
                .build();
    }


}
