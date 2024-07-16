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
import java.util.List;

@RestController
@RequestMapping("/api/v1/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/create")
    public ResponseEntity<ReadDtoAppointment> registerDoctor(
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



}