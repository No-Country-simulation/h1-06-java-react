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
    Optional<List<Treatment>> findByMedicalProcedureCodeAndPatientId(String code, String patientId);
    Optional<List<Treatment>> findByMedicalProcedureNameAndPatientId(String name, String patientId);
    Optional<List<Treatment>> findByPatientId(String patientId);
    Optional<List<Treatment>> findByDoctorId(String doctorId);
    Optional<List<Treatment>> findByDateAndPatientId(LocalDateTime date, String patientId);
    Optional<List<Treatment>> findByTreatmentStatus(TreatmentStatus treatmentStatus);

    @Query("SELECT t FROM Treatment t JOIN t.pathologyList p WHERE p = :pathology " +
            "AND t.patient.id = :patientId")
    Optional<List<Treatment>> findByPathologyAndPatientId(
            @Param("pathology") Pathology pathology,
            @Param("patientId") String patientId
    );

    @Query("SELECT t FROM Treatment t JOIN t.medicineList m WHERE m = :medicine " +
            "AND t.patient.id = :patientId")
    Optional<List<Treatment>> findByMedicineAndPatientId(
            @Param("medicine") Medicine medicine,
            @Param("patientId") String patientId
    );

    @Query("SELECT t FROM Treatment t JOIN t.pathologyList p WHERE p.code = :pathologyCode " +
            "AND t.treatmentStatus = 0 AND t.donationData.donationType = 'DONACION_CRUZADA' ")
    List<Treatment> findForCrossDonation(String pathologyCode);
}
