package com.sdn.health.doctor.controller;

import com.sdn.health.doctor.dto.request.DoctorRequestDto;
import com.sdn.health.doctor.dto.response.DoctorResponseDto;
import com.sdn.health.doctor.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService service;

    @PostMapping
    public ResponseEntity<DoctorResponseDto> createDoctor(
            @Valid @RequestBody DoctorRequestDto dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createDoctor(dto));
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponseDto>> getAllDoctors() {
        return ResponseEntity.ok(service.getAllDoctors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDto> getDoctorById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getDoctorById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<DoctorResponseDto> getDoctorByName(@RequestParam String name) {
        return ResponseEntity.ok(service.getDoctorByName(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDto> updateDoctor(
            @PathVariable Long id,
            @Valid @RequestBody DoctorRequestDto dto) {

        return ResponseEntity.ok(service.updateDoctor(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteDoctor(@PathVariable Long id) {
        service.deleteDoctor(id);
        return ResponseEntity.ok(service.deleteDoctor(id));
    }
}
