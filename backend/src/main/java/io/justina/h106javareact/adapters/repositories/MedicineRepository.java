package io.justina.h106javareact.adapters.repositories;

import io.justina.h106javareact.domain.entities.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, String> {
    boolean existsByCode(String code);
    Optional<Medicine> findByCode(String code);
}
