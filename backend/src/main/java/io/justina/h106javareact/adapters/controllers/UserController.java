package io.justina.h106javareact.adapters.controllers;

import io.justina.h106javareact.adapters.dtos.user.CreateDtoPatient;
import io.justina.h106javareact.adapters.dtos.user.ReadDtoPatient;
import io.justina.h106javareact.adapters.dtos.user.UpdateDtoPatient;
import io.justina.h106javareact.application.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("unused")
@RequestMapping("/api/v1/user")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ReadDtoPatient> registerPatient(
            @RequestBody @Valid CreateDtoPatient createPatient){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.userService.createPatient(createPatient));
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
