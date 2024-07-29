package io.justina.h106javareact.adapters.controllers;

import io.justina.h106javareact.adapters.dtos.medicalProcedure.ReadDtoMedicalProcedure;
import io.justina.h106javareact.application.services.MedicalProcedureService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RequestMapping("/api/v1/medicalProcedure")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class MedicalProcedureController {

    private final MedicalProcedureService medicalProcedureService;

    @GetMapping
    public ResponseEntity<List<ReadDtoMedicalProcedure>> findAllProcedures(){
        return ResponseEntity.ok(medicalProcedureService.readAll());
    }
/*
    @GetMapping("/scraping")
    public ResponseEntity<Boolean> scraping() throws IOException {
        return ResponseEntity.ok(medicalProcedureService.scraping());
    }*/
}
