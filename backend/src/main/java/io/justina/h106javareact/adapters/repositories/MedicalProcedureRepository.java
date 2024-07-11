package io.justina.h106javareact.adapters.repositories;

import io.justina.h106javareact.domain.entities.MedicalProcedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicalProcedureRepository extends JpaRepository<MedicalProcedure, String> {
    Optional<MedicalProcedure> findByCode(String code);
}
