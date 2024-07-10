package io.justina.h106javareact.adapters.controllers;
import io.justina.h106javareact.adapters.dtos.pathology.ReadDtoPathology;
import io.justina.h106javareact.application.services.PathologyService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.List;

@RequestMapping("/api/v1/pathology")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class PathologyController {

    private final PathologyService pathologyService;

    @GetMapping
    public ResponseEntity<List<ReadDtoPathology>> findAllPathologies(){
        return ResponseEntity.ok(pathologyService.readAll());
    }

    @GetMapping("/scraping")
    public ResponseEntity<Boolean> scrapping() throws IOException {
        return ResponseEntity.ok(pathologyService.scraping());
    }

}
