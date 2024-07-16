package io.justina.h106javareact.adapters.repositories;
import io.justina.h106javareact.domain.entities.Medicine;
import io.justina.h106javareact.domain.entities.Pathology;
import io.justina.h106javareact.domain.entities.Treatment;
import io.justina.h106javareact.domain.entities.enums.TreatmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TreatmentRepository extends JpaRepository<Treatment, String> {
    Optional<List<Treatment>> findByMedicalProcedureCode(String code);
    Optional<List<Treatment>> findByMedicalProcedureName(String name);
    Optional<List<Treatment>> findByPatientId(String patientId);
    Optional<List<Treatment>> findByDoctorId(String doctorId);
    Optional<List<Treatment>> findByDate(LocalDateTime date);
    Optional<List<Treatment>> findByTreatmentStatus(TreatmentStatus treatmentStatus);

    @Query("SELECT t FROM Treatment t JOIN t.pathologyList p WHERE p = :pathology")
    Optional<List<Treatment>> findByPathology(@Param("pathology") Pathology pathology);

    @Query("SELECT t FROM Treatment t JOIN t.medicineList m WHERE m = :medicine")
    Optional<List<Treatment>> findByMedicine(@Param("medicine") Medicine medicine);

}
