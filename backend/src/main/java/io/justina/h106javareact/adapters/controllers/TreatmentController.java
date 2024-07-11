package io.justina.h106javareact.adapters.controllers;

import io.justina.h106javareact.adapters.dtos.relative.CreateDtoRelative;
import io.justina.h106javareact.adapters.dtos.relative.ReadDtoRelative;
import io.justina.h106javareact.adapters.dtos.treatment.CreateDtoTreatment;
import io.justina.h106javareact.adapters.dtos.treatment.ReadDtoTreatment;
import io.justina.h106javareact.application.services.TreatmentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/treatment")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class TreatmentController {

    private final TreatmentService treatmentService;

    @PostMapping("/create")
    public ResponseEntity<ReadDtoTreatment> createTreatment(
            @RequestBody @Valid CreateDtoTreatment createTreatment){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.treatmentService.create(createTreatment));
    }
}

//Crear
//Ver uno por id
//Ver todos por paciente id
//Editar (para cambiar estado al tratamiento)
