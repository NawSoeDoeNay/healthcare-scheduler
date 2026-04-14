package com.sdn.health.appointment.controller;

import com.sdn.health.appointment.dto.request.AppointmentReqDto;
import com.sdn.health.appointment.dto.response.AppointmentDto;
import com.sdn.health.appointment.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService service;

    @PostMapping
    public ResponseEntity<AppointmentDto> book(
            @Valid @RequestBody AppointmentReqDto dto
            ) {
        return ResponseEntity.ok(service.bookAppointment(dto));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable Long id){
        service.cancelAppointment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentDto>> getByDoctor(
            @PathVariable Long doctorId
    ){
        return ResponseEntity.ok(service.getAppointmentsByDoctor(doctorId));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppointmentDto>> getByPatient(
            @PathVariable Long patientId
    ){
        return ResponseEntity.ok(service.getAppointmentsByPatient(patientId));
    }


}
