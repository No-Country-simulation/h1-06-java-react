package h1_06_java_react.h1_06_java_react.adapters.controllers;

import h1_06_java_react.h1_06_java_react.adapters.dtos.patient.CreateDtoPatient;
import h1_06_java_react.h1_06_java_react.adapters.dtos.patient.ReadDtoPatient;
import h1_06_java_react.h1_06_java_react.adapters.dtos.patient.UpdateDtoPatient;
import h1_06_java_react.h1_06_java_react.application.services.PatientService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/patient")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class PatientController {

    private final PatientService patientService;

    @PostMapping("/register")
    public ResponseEntity<ReadDtoPatient> registerPatient(
            @RequestBody @Valid @NotNull CreateDtoPatient createPatient){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.patientService.createPatient(createPatient));
    }

    @GetMapping("/id/{id}/{active}")
    public ResponseEntity<ReadDtoPatient>  findPatientById(
            @PathVariable String id, @PathVariable Boolean active){
        return ResponseEntity.ok(patientService.readPatientById(id, active));
    }

    @GetMapping("/email/{email}/{active}")
    public ResponseEntity<ReadDtoPatient>  findPatientByEmail(
            @PathVariable String email, @PathVariable Boolean active){
        return ResponseEntity.ok(patientService.readPatientByEmail(email, active));
    }

    @PutMapping("/update")
    public ResponseEntity<ReadDtoPatient> updatePatient(
            @RequestBody @Valid UpdateDtoPatient updatePatient){
        return ResponseEntity.ok(patientService.updatePatient(updatePatient));
    }

    @DeleteMapping("/toggle/{id}")
    public ResponseEntity<Boolean> togglePatient(@PathVariable String id){
        return ResponseEntity.ok(patientService.togglePatient(id));
    }

}
