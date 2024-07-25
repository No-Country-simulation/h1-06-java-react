package io.justina.h106javareact.adapters.repositories;
import io.justina.h106javareact.domain.entities.Pathology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PathologyRepository extends JpaRepository <Pathology, String> {
    Optional<Pathology> findByCode(String code);
}
