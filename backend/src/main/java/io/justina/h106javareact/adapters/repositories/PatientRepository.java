package io.justina.h106javareact.adapters.repositories;

import io.justina.h106javareact.domain.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {
    Optional<Patient> findByIdAndActive(String id, Boolean active);
    Optional<Patient> findByEmailAndActive(String email, Boolean active);
    Optional<Patient> findByEmail(String email); //Necesario para corroborar previo a crear.
}

