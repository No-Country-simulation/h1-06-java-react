package io.justina.h106javareact.adapters.controllers;

import io.justina.h106javareact.adapters.dtos.doctor.CreateDtoDoctor;
import io.justina.h106javareact.adapters.dtos.doctor.ReadDtoDoctor;
import io.justina.h106javareact.adapters.dtos.patient.CreateDtoPatient;
import io.justina.h106javareact.adapters.dtos.patient.ReadDtoPatient;
import io.justina.h106javareact.adapters.dtos.patient.UpdateDtoPatient;
import io.justina.h106javareact.adapters.dtos.relative.*;
import io.justina.h106javareact.application.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("unused")
@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {

    private final UserService userService;

    @PostMapping("/patient/register")
    public ResponseEntity<ReadDtoPatient> registerPatient(
            @RequestBody @Valid CreateDtoPatient createPatient){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.userService.createPatient(createPatient));
    }

    @PostMapping("/doctor/register")
    public ResponseEntity<ReadDtoDoctor> registerDoctor(
            @RequestBody @Valid CreateDtoDoctor createDoctor){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.userService.createDoctor(createDoctor));
    }

    @PostMapping("/relative/register")
    public ResponseEntity<ReadDtoRelative> registerRelative(
            @RequestBody @Valid CreateDtoRelative createRelative){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.userService.createRelative(createRelative));
    }

    @GetMapping("/id/{id}/{active}")
    public ResponseEntity<ReadDtoPatient>  findPatientById(
            @PathVariable String id, @PathVariable Boolean active){
        return ResponseEntity.ok(userService.readPatientById(id, active));
    }

    @GetMapping("/email/{email}/{active}")
    public ResponseEntity<ReadDtoPatient>  findPatientByEmail(
            @PathVariable String email, @PathVariable Boolean active){
        return ResponseEntity.ok(userService.readPatientByEmail(email, active));
    }

    @PutMapping("/update")
    public ResponseEntity<ReadDtoPatient> updatePatient(
            @RequestBody @Valid UpdateDtoPatient updatePatient){
        return ResponseEntity.ok(userService.updatePatient(updatePatient));
    }

    @DeleteMapping("/toggle/{id}")
    public ResponseEntity<Boolean> togglePatient(@PathVariable String id){
        return ResponseEntity.ok(userService.togglePatient(id));
    }

}
