package io.justina.h106javareact.adapters.controllers;

import io.justina.h106javareact.adapters.dtos.relative.CreateDtoRelative;
import io.justina.h106javareact.adapters.dtos.relative.ReadDtoRelative;
import io.justina.h106javareact.adapters.dtos.relative.UpdateDtoRelative;
import io.justina.h106javareact.application.services.RelativeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/relative")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class RelativeController {

    private final RelativeService relativeService;

    @PostMapping("/register")
    public ResponseEntity<ReadDtoRelative> registerRelative(
            @RequestBody @Valid CreateDtoRelative createRelative){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.relativeService.createRelative(createRelative));
    }

    @GetMapping("/id/{id}/{active}")
    public ResponseEntity<ReadDtoRelative> findRelativeById(
            @PathVariable String id, @PathVariable Boolean active){
        return ResponseEntity.ok(relativeService.readRelativeById(id, active));
    }

    @GetMapping("/email/{email}/{active}")
    public ResponseEntity<ReadDtoRelative>  findRelativeByEmail(
            @PathVariable String email, @PathVariable Boolean active){
        return ResponseEntity.ok(relativeService.readRelativeByEmail(email, active));
    }

    @PutMapping("/update")
    public ResponseEntity<ReadDtoRelative> updateRelative(
            @RequestBody @Valid UpdateDtoRelative updateRelative){
        return ResponseEntity.ok(relativeService.updateRelative(updateRelative));
    }

    @DeleteMapping("/toggle/{id}")
    public ResponseEntity<Boolean> toggleRelative(@PathVariable String id){
        return ResponseEntity.ok(relativeService.toggleRelative(id));
    }
}
