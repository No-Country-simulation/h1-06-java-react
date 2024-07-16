package io.justina.h106javareact.adapters.repositories;

import io.justina.h106javareact.domain.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {
/*
    @Query(value = "SELECT * FROM appointment WHERE doctor_id = :doctorId " +
            "AND active = :active AND date BETWEEN :startDate AND :endDate",
            nativeQuery = true)
    List<Appointment> findAppointmentsByDoctorIdAndDateRange(
            @Param("doctorId") String doctorId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("active") Boolean active
    );

    @Query(value = "SELECT * FROM appointment WHERE assistent_id = :assistentId " +
            "AND active = :active AND date BETWEEN :startDate AND :endDate",
            nativeQuery = true)
    List<Appointment> findAppointmentsByPatientIdAndDateRange(
            @Param("patientId") String patientId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("active") Boolean active
    );

    @Query("SELECT a FROM Appointment a WHERE a.doctor.id = :doctorId AND a.active = :active")
    List<Appointment> findAppointmentsByDoctorId(String doctorId, Boolean active);

    @Query("SELECT a FROM Appointment a WHERE a.patient.id = :patientId AND a.active = :active")
    List<Appointment> findAppointmentsByPatientId(String patientId, Boolean active);

    @Query(value = "SELECT a FROM Appointment a where a.active = :active AND year(a.date) = :year " +
            " AND month(a.date) = :month AND day(a.date) = :day")
    List<Appointment> findAppointmentsByDateByActive(Boolean active, int day, int month, int year);

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM appointment WHERE doctor_id = :doctorId AND patient_id = :patientId AND DATE(appointment.date) = DATE(:date)", nativeQuery = true)
    boolean existsByDoctorAndPatient(@Param("doctorId") String doctorId,@Param("patientId") String patientId,@Param("date") LocalDateTime date);
 */
}

