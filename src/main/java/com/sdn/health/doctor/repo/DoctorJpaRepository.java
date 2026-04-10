package com.sdn.health.doctor.repo;

import com.sdn.health.doctor.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorJpaRepository extends JpaRepository<Doctor, Long> {
}
