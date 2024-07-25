package io.justina.h106javareact.adapters.controllers;

import io.justina.h106javareact.adapters.dtos.doctor.ReadDtoDoctor;
import io.justina.h106javareact.adapters.dtos.doctor.UpdateDtoDoctor;
import io.justina.h106javareact.adapters.dtos.medicalInformation.AddDtoMedicalInformation;
import io.justina.h106javareact.adapters.dtos.patient.CreateDtoPatient;
import io.justina.h106javareact.adapters.dtos.patient.ReadDtoPatient;
import io.justina.h106javareact.adapters.dtos.patient.UpdateDtoPatient;
import io.justina.h106javareact.application.services.PatientService;
import io.justina.h106javareact.application.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/patient")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class PatientController {

    private final PatientService patientService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ReadDtoPatient> registerPatient(
            @RequestBody @Valid CreateDtoPatient createPatient) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.patientService.createPatient(createPatient));
    }

    @GetMapping("/id/{id}/{active}")
    public ResponseEntity<ReadDtoPatient> findPatientById(
            @PathVariable String id, @PathVariable Boolean active){
        return ResponseEntity.ok(userService.readById(id, active));
    }

    @GetMapping("/email/{email}/{active}")
    public ResponseEntity<ReadDtoPatient>  findPatientByEmail(
            @PathVariable String email, @PathVariable Boolean active){
        return ResponseEntity.ok(userService.readByEmail(email, active));
    }

    @GetMapping("/surname/{surname}/{active}")
    public ResponseEntity<List<ReadDtoPatient>> findPatientBySurname(
            @PathVariable String surname, @PathVariable Boolean active){
        return ResponseEntity.ok(userService.readBySurname(surname, active));
    }

    @PutMapping("/update")
    public ResponseEntity<ReadDtoPatient> updatePatient(
            @RequestBody @Valid UpdateDtoPatient updatePatient){
        return ResponseEntity.ok(patientService.updatePatient(updatePatient));
    }

    @DeleteMapping("/toggle/{id}")
    public ResponseEntity<Boolean> togglePatient(@PathVariable String id){
        return ResponseEntity.ok(userService.toggle(id));
    }

    @PutMapping("/medicalInformation")
    public ResponseEntity<ReadDtoPatient> updateMedicalInformation(
            @RequestBody @Valid AddDtoMedicalInformation medicalInformation) throws BadRequestException {
        return ResponseEntity.ok(patientService.updateMedicalInformation(medicalInformation));
    }
}

