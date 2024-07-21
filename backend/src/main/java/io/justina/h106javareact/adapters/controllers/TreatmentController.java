package io.justina.h106javareact.adapters.controllers;
import io.justina.h106javareact.adapters.dtos.treatment.CreateDtoTreatment;
import io.justina.h106javareact.adapters.dtos.treatment.ReadDtoTreatment;
import io.justina.h106javareact.adapters.dtos.treatment.UpdateDtoTreatment;
import io.justina.h106javareact.application.services.TreatmentService;
import io.justina.h106javareact.application.validations.Validations;
import io.justina.h106javareact.infrastructure.security.JwtService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RequestMapping("/api/v1/treatment")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class TreatmentController {

    private final TreatmentService treatmentService;
    private final JwtService jwtService;
    private final Validations validations;

    @PostMapping("/create")
    public ResponseEntity<ReadDtoTreatment> createTreatment(
            @RequestBody @Valid CreateDtoTreatment createTreatment){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.treatmentService.create(createTreatment));
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<ReadDtoTreatment> findTreatmentById(
            @PathVariable String id, @PathVariable String patientId,
            @RequestHeader("Authorization") String token){
        String role = jwtService.getRoles(token);
        validations.checkRelativeOrDoctorValidation(patientId, role);
        return ResponseEntity.ok(treatmentService.findById(id));
    }

    @GetMapping("/medicalProcedureCode/{code}/{patientId}")
    public ResponseEntity<List<ReadDtoTreatment>> findTreatmentByProcedureCode(
            @PathVariable String code, @PathVariable String patientId,
            @RequestHeader("Authorization") String token){
        token = token.substring(7);
        String role = jwtService.getRoles(token);
        validations.checkRelativeOrDoctorValidation(patientId, role);
        return ResponseEntity.ok(treatmentService.findByMedicalProcedureCode(code, patientId));
    }

    @GetMapping("/medicalProcedureName/{name}/{patientId}")
    public ResponseEntity<List<ReadDtoTreatment>> findTreatmentByProcedureName(
            @PathVariable String name, @PathVariable String patientId,
            @RequestHeader("Authorization") String token){
        token = token.substring(7);
        String role = jwtService.getRoles(token);
        validations.checkRelativeOrDoctorValidation(patientId, role);
        return ResponseEntity.ok(treatmentService.findByMedicalProcedureName(name, patientId));
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<List<ReadDtoTreatment>> findTreatmentByPatientId(
            @PathVariable String id){
        return ResponseEntity.ok(treatmentService.findByPatientId(id));
    }

    @GetMapping("/doctor/{id}")
    public ResponseEntity<List<ReadDtoTreatment>> findTreatmentByDoctorId(
            @PathVariable String id){
        return ResponseEntity.ok(treatmentService.findByDoctorId(id));
    }

    @GetMapping("/date/{date}/{patientId}")
    public ResponseEntity<List<ReadDtoTreatment>> findTreatmentByDate(
            @PathVariable String date, @PathVariable String patientId,
            @RequestHeader("Authorization") String token){
        token = token.substring(7);
        String role = jwtService.getRoles(token);
        validations.checkRelativeOrDoctorValidation(patientId, role);
        return ResponseEntity.ok(treatmentService.findByDate(date, patientId));
    }

    @GetMapping("/status/{status}/{patientId}")
    public ResponseEntity<List<ReadDtoTreatment>> findTreatmentByStatus(
            @PathVariable String status, @PathVariable String patientId,
            @RequestHeader("Authorization") String token){
        token = token.substring(7);
        String role = jwtService.getRoles(token);
        validations.checkRelativeOrDoctorValidation(patientId, role);
        return ResponseEntity.ok(treatmentService.findByTreatmentStatus(status, patientId));
    }

    @GetMapping("/pathology/{code}/{patientId}")
    public ResponseEntity<List<ReadDtoTreatment>> findTreatmentByPathologyCode(
            @PathVariable String code, @PathVariable String patientId,
            @RequestHeader("Authorization") String token){
        token = token.substring(7);
        String role = jwtService.getRoles(token);
        validations.checkRelativeOrDoctorValidation(patientId, role);
        return ResponseEntity.ok(treatmentService.findByPathologyCode(code, patientId));
    }

    @GetMapping("/medicine/{code}/{patientId}")
    public ResponseEntity<List<ReadDtoTreatment>> findTreatmentByMedicineCode(
            @PathVariable String code, @PathVariable String patientId,
            @RequestHeader("Authorization") String token){
        token = token.substring(7);
        String role = jwtService.getRoles(token);
        validations.checkRelativeOrDoctorValidation(patientId, role);
        return ResponseEntity.ok(treatmentService.findByMedicineCode(code, patientId));
    }

    @PutMapping("/update")
    public ResponseEntity<ReadDtoTreatment> updateTreatment(
            @RequestBody @Valid UpdateDtoTreatment updateTreatment){
        return ResponseEntity.ok(treatmentService.update(updateTreatment));
    }

    @GetMapping("/patient/{id}/medicalRecordPdf")
    public ResponseEntity<InputStreamResource> downloadPDF
            (@PathVariable String id) throws IOException {
        validations.checkRelativeValidation(id);
        var pdfResource = treatmentService.downloadMedicalRecordPDF(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=medicalRecord.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfResource);
    }
}


