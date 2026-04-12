package com.sdn.health.doctor.repo;

import com.sdn.health.doctor.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorJpaRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor>  findByNameContainingIgnoreCase(String name);
}
