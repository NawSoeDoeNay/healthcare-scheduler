package com.sdn.health.doctor.dto.request;


import com.sdn.health.common.code.constants.ExperienceLevel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequestDto {

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    private String specialization;

    @NotNull
    private ExperienceLevel level;
}
