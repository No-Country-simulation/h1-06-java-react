package io.justina.h106javareact.adapters.controllers;

import io.justina.h106javareact.adapters.dtos.appointment.CreateDtoAppointment;
import io.justina.h106javareact.adapters.dtos.appointment.ReadDtoAppointment;
import io.justina.h106javareact.adapters.dtos.appointment.UpdateDtoAppointment;
import io.justina.h106javareact.adapters.dtos.treatment.ReadDtoTreatment;
import io.justina.h106javareact.adapters.dtos.treatment.UpdateDtoTreatment;
import io.justina.h106javareact.application.services.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/create")
    public ResponseEntity<ReadDtoAppointment> createAppointment(
            @RequestBody @Valid CreateDtoAppointment createAppointment){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.appointmentService.create(createAppointment));
    }

    @PutMapping("/update")
    public ResponseEntity<ReadDtoAppointment> updateAppointment(
            @RequestBody @Valid UpdateDtoAppointment updateAppointment)
            throws BadRequestException {
        return ResponseEntity.ok(appointmentService.update(updateAppointment));
    }


    @GetMapping("/{id}/{active}")
    public ResponseEntity<ReadDtoAppointment> findAppointmentById(
            @PathVariable String id, @PathVariable Boolean active){
        return ResponseEntity.ok(appointmentService.findByIdAndActive(id, active));
    }

    @GetMapping("/doctor/{id}/{active}")
    public ResponseEntity<List<ReadDtoAppointment>> findAppointmentsByDoctorId(
            @PathVariable String id, @PathVariable Boolean active){
        return ResponseEntity.ok(appointmentService.findByDoctorId(id, active));
    }

    @GetMapping("/patient/{id}/{active}")
    public ResponseEntity<List<ReadDtoAppointment>> findAppointmentsByPatientId(
            @PathVariable String id, @PathVariable Boolean active){
        return ResponseEntity.ok(appointmentService.findByPatientId(id, active));
    }

    @GetMapping("/doctor/{id}/{date}/{active}")
    public ResponseEntity<List<ReadDtoAppointment>> findAppointmentsByDoctorIdAndDate(
            @PathVariable String id, @PathVariable LocalDateTime date,
            @PathVariable Boolean active){
        return ResponseEntity.ok(appointmentService
                .findByDoctorIdAndDate(id, date, active));
    }

    @GetMapping("/patient/{id}/{date}/{active}")
    public ResponseEntity<List<ReadDtoAppointment>> findAppointmentsByPatientIdAndDate(
            @PathVariable String id, @PathVariable LocalDateTime date,
            @PathVariable Boolean active){
        return ResponseEntity.ok(appointmentService
                .findByPatientIdAndDate(id, date, active));
    }

    @GetMapping("/doctor/{id}/{startDate}/{endDate}/{active}")
    public ResponseEntity<List<ReadDtoAppointment>> findAppointmentsByDoctorIdAndDateRange(
            @PathVariable String id, @PathVariable LocalDateTime startDate,
            @PathVariable LocalDateTime endDate, @PathVariable Boolean active){
        return ResponseEntity.ok(appointmentService
                .findByDoctorIdAndDateRange(id, startDate, endDate, active));
    }

    @GetMapping("/patient/{id}/{startDate}/{endDate}/{active}")
    public ResponseEntity<List<ReadDtoAppointment>> findAppointmentsByPatientIdAndDateRange(
            @PathVariable String id, @PathVariable LocalDateTime startDate,
            @PathVariable LocalDateTime endDate, @PathVariable Boolean active){
        return ResponseEntity.ok(appointmentService
                .findByPatientIdAndDateRange(id, startDate, endDate, active));
    }

    @GetMapping("/doctor/{id}/{startDate}/{endDate}/available")
    public ResponseEntity<List<LocalDateTime>> findAvailableAppointmentsByDoctorIdAndDate(
            @PathVariable String id, @PathVariable LocalDateTime startDate, @PathVariable LocalDateTime endDate ){
        return ResponseEntity.ok(appointmentService
                .findByDoctorIdAndDateAvailable(id, startDate, endDate));
    }
    @DeleteMapping("/toggle/{id}")
    public ResponseEntity<Boolean> toggle(@PathVariable String id) throws BadRequestException {
        return ResponseEntity.ok(appointmentService.toggle(id));
    }

}