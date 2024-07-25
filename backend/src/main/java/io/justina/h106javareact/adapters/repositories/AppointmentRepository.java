package io.justina.h106javareact.adapters.repositories;

import io.justina.h106javareact.domain.entities.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {

    Appointment findByIdAndActive (String id, Boolean active);
    List<Appointment> findAppointmentsByDoctorIdAndActive(String doctorId, Boolean active);
    List<Appointment> findAppointmentsByPatientIdAndActive(String patientId, Boolean active);

    @Query(value = "SELECT * FROM appointment WHERE doctor_id = :doctorId " +
            "AND active = :active AND date = :date",
            nativeQuery = true)
    List<Appointment> findAppointmentsByDoctorIdAndDate(
            @Param("doctorId") String doctorId,
            @Param("date") LocalDateTime date,
            @Param("active") Boolean active
    );

    @Query(value = "SELECT * FROM appointment WHERE patient_id = :patientId " +
            "AND active = :active AND date = :date",
            nativeQuery = true)
    List<Appointment> findAppointmentsByPatientIdAndDate(
            @Param("patientId") String patientId,
            @Param("date") LocalDateTime date,
            @Param("active") Boolean active
    );

    @Query(value = "SELECT * FROM appointment WHERE doctor_id = :doctorId " +
            "AND active = :active AND date BETWEEN :startDate AND :endDate",
            nativeQuery = true)
    List<Appointment> findAppointmentsByDoctorIdAndDateRange(
            @Param("doctorId") String doctorId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("active") Boolean active
    );

    @Query(value = "SELECT * FROM appointment WHERE patient_id = :patientId " +
            "AND active = :active AND date BETWEEN :startDate AND :endDate",
            nativeQuery = true)
    List<Appointment> findAppointmentsByPatientIdAndDateRange(
            @Param("patientId") String patientId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("active") Boolean active
    );



}

