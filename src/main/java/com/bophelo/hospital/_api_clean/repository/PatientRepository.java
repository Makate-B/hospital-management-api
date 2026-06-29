package com.bophelo.hospital._api_clean.repository;

import com.bophelo.hospital._api_clean.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByMemberNumber(String memberNumber);

    boolean existsByMemberNumber(String memberNumber);

    List<Patient> findByDoctorMemberNumber(String doctorMemberNumber);
}
