package io.justina.h106javareact.adapters.repositories;

import io.justina.h106javareact.domain.entities.Antibody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AntibodyRepository extends JpaRepository<Antibody, String> {
}
