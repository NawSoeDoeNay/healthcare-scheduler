package com.sdn.health.patient.controller;

import com.sdn.health.patient.dto.response.PatientDto;
import com.sdn.health.patient.dto.request.PatientReqDto;
import com.sdn.health.patient.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientController {


    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientDto> createPatient(@Valid @RequestBody PatientReqDto patientDto) {
        PatientDto created = patientService.createPatient(patientDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatients() {
        List<PatientDto> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatientById(
            @PathVariable Long id
    ) {
        PatientDto patients = patientService.getPatientById(id);
        return ResponseEntity.ok(patients);
    }

}
