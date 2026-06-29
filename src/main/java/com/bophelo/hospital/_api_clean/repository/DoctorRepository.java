package com.bophelo.hospital._api_clean.repository;

import com.bophelo.hospital._api_clean.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByMemberNumber(String memberNumber);

    boolean existsByMemberNumber(String memberNumber);
}
