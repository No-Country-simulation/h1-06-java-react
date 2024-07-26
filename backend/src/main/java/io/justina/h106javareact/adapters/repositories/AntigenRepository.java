package io.justina.h106javareact.adapters.repositories;

import io.justina.h106javareact.domain.entities.Antigen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AntigenRepository extends JpaRepository<Antigen, String> {
}
