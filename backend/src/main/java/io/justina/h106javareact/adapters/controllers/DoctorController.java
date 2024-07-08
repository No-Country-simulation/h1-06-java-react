package io.justina.h106javareact.adapters.controllers;

import io.justina.h106javareact.adapters.dtos.doctor.CreateDtoDoctor;
import io.justina.h106javareact.adapters.dtos.doctor.ReadDtoDoctor;
import io.justina.h106javareact.adapters.dtos.doctor.UpdateDtoDoctor;
import io.justina.h106javareact.application.services.DoctorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/doctor")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping("/register")
    public ResponseEntity<ReadDtoDoctor> registerDoctor(
            @RequestBody @Valid CreateDtoDoctor createDoctor){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.doctorService.createDoctor(createDoctor));
    }

    @GetMapping("/id/{id}/{active}")
    public ResponseEntity<ReadDtoDoctor> findDoctorById(
            @PathVariable String id, @PathVariable Boolean active){
        return ResponseEntity.ok(doctorService.readDoctorById(id, active));
    }

    @GetMapping("/email/{email}/{active}")
    public ResponseEntity<ReadDtoDoctor>  findDoctorByEmail(
            @PathVariable String email, @PathVariable Boolean active){
        return ResponseEntity.ok(doctorService.readDoctorByEmail(email, active));
    }

    @PutMapping("/update")
    public ResponseEntity<ReadDtoDoctor> updateDoctor(
            @RequestBody @Valid UpdateDtoDoctor updateDoctor){
        return ResponseEntity.ok(doctorService.updateDoctor(updateDoctor));
    }

    @DeleteMapping("/toggle/{id}")
    public ResponseEntity<Boolean> toggleDoctor(@PathVariable String id){
        return ResponseEntity.ok(doctorService.toggleDoctor(id));
    }
}
