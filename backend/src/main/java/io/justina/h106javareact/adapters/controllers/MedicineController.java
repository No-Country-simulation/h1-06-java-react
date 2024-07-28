package io.justina.h106javareact.adapters.controllers;

import io.justina.h106javareact.adapters.dtos.medicine.ReadDtoMedicine;
import io.justina.h106javareact.application.services.MedicineService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/medicine")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class MedicineController {

    private final MedicineService medicineService;

    @GetMapping
    public ResponseEntity<List<ReadDtoMedicine>> findAllMedicines(){
        return ResponseEntity.ok(medicineService.readAll());
    }

    /*
    @GetMapping("/scraping")
    public ResponseEntity<Boolean> scrapping(){
        return ResponseEntity.ok(medicineService.scrapForMedicines());
    }*/

}
