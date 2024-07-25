package io.justina.h106javareact.adapters.repositories;

import io.justina.h106javareact.domain.entities.PatientData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDataRepository extends JpaRepository<PatientData, String> {
}
