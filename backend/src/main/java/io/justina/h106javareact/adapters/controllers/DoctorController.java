package io.justina.h106javareact.adapters.controllers;

import io.justina.h106javareact.adapters.dtos.doctor.CreateDtoDoctor;
import io.justina.h106javareact.adapters.dtos.doctor.ReadDtoDoctor;
import io.justina.h106javareact.adapters.dtos.doctor.UpdateDtoDoctor;
import io.justina.h106javareact.application.services.DoctorService;
import io.justina.h106javareact.application.services.UserService;
import io.justina.h106javareact.domain.entities.enums.Specialty;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/api/v1/doctor")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class DoctorController {

    private final DoctorService doctorService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ReadDtoDoctor> registerDoctor(
            @RequestBody @Valid CreateDtoDoctor createDoctor){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.doctorService.createDoctor(createDoctor));
    }

    @GetMapping("/id/{id}/{active}")
    public ResponseEntity<ReadDtoDoctor> findDoctorById(
            @PathVariable String id, @PathVariable Boolean active){
        return ResponseEntity.ok(userService.readById(id, active));
    }

    @GetMapping("/email/{email}/{active}")
    public ResponseEntity<ReadDtoDoctor>  findDoctorByEmail(
            @PathVariable String email, @PathVariable Boolean active){
        return ResponseEntity.ok(userService.readByEmail(email, active));
    }

    @GetMapping("/specialty/{specialty}")
    public ResponseEntity<List<ReadDtoDoctor>>  findDoctorsBySpecialty(
            @PathVariable String specialty){
        return ResponseEntity.ok(doctorService.readBySpecialty(specialty));
    }

    @PutMapping("/update")
    public ResponseEntity<ReadDtoDoctor> updateDoctor(
            @RequestBody @Valid UpdateDtoDoctor updateDoctor){
        return ResponseEntity.ok(doctorService.updateDoctor(updateDoctor));
    }

    @DeleteMapping("/toggle/{id}")
    public ResponseEntity<Boolean> toggleDoctor(@PathVariable String id){
        return ResponseEntity.ok(userService.toggle(id));
    }

    @GetMapping("/specialties")
    public ResponseEntity<List<Specialty>> specialties(){
        var specialties = new ArrayList<Specialty>(Arrays.asList(Specialty.values()));
        return ResponseEntity.ok(specialties);
    }
}
