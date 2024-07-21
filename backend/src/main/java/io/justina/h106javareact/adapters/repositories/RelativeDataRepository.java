package io.justina.h106javareact.adapters.repositories;

import io.justina.h106javareact.domain.entities.RelativeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RelativeDataRepository extends JpaRepository<RelativeData, String> {
    Optional<RelativeData> findByAssistedEmail(String assistedEmail);
}
