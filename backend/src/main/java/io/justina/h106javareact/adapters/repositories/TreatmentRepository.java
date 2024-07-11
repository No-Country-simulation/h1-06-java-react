package io.justina.h106javareact.adapters.repositories;
import io.justina.h106javareact.domain.entities.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentRepository extends JpaRepository<Treatment, String> {

}
