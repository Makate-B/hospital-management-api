package com.bophelo.hospital._api_clean.repository;

import com.bophelo.hospital._api_clean.model.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NurseRepository extends JpaRepository<Nurse, Long> {

    Optional<Nurse> findByMemberNumber(String memberNumber);

    boolean existsByMemberNumber(String memberNumber);
}
