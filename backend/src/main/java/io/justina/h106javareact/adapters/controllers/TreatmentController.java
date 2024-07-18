package io.justina.h106javareact.adapters.controllers;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import io.justina.h106javareact.adapters.dtos.treatment.CreateDtoTreatment;
import io.justina.h106javareact.adapters.dtos.treatment.ReadDtoTreatment;
import io.justina.h106javareact.adapters.dtos.treatment.UpdateDtoTreatment;
import io.justina.h106javareact.application.services.TreatmentService;
import io.justina.h106javareact.domain.entities.Treatment;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

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

    @GetMapping("/id/{id}")
    public ResponseEntity<ReadDtoTreatment> findTreatmentById(
            @PathVariable String id){
        return ResponseEntity.ok(treatmentService.findById(id));
    }

    @GetMapping("/medicalProcedureCode/{code}")
    public ResponseEntity<List<ReadDtoTreatment>> findTreatmentByProcedureCode(
            @PathVariable String code){
        return ResponseEntity.ok(treatmentService.findByMedicalProcedureCode(code));
    }

    @GetMapping("/medicalProcedureName/{name}")
    public ResponseEntity<List<ReadDtoTreatment>> findTreatmentByProcedureName(
            @PathVariable String name){
        return ResponseEntity.ok(treatmentService.findByMedicalProcedureName(name));
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

    @GetMapping("/date/{date}")
    public ResponseEntity<List<ReadDtoTreatment>> findTreatmentByDate(
            @PathVariable String date){
        return ResponseEntity.ok(treatmentService.findByDate(date));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ReadDtoTreatment>> findTreatmentByStatus(
            @PathVariable String status){
        return ResponseEntity.ok(treatmentService.findByTreatmentStatus(status));
    }

    @GetMapping("/pathology/{code}")
    public ResponseEntity<List<ReadDtoTreatment>> findTreatmentByPathologyCode(
            @PathVariable String code){
        return ResponseEntity.ok(treatmentService.findByPathologyCode(code));
    }

    @GetMapping("/medicine/{code}")
    public ResponseEntity<List<ReadDtoTreatment>> findTreatmentByMedicineCode(
            @PathVariable String code){
        return ResponseEntity.ok(treatmentService.findByMedicineCode(code));
    }

    @PutMapping("/update")
    public ResponseEntity<ReadDtoTreatment> updateTreatment(
            @RequestBody @Valid UpdateDtoTreatment updateTreatment){
        return ResponseEntity.ok(treatmentService.update(updateTreatment));
    }

    @GetMapping("/patient/{id}/medicalRecordPdf")
    public ResponseEntity<InputStreamResource> downloadPDF
            (@PathVariable String id) throws IOException {
        List<ReadDtoTreatment> medicalRecord = treatmentService.findByPatientId(id);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(out);
        com.itextpdf.kernel.pdf.PdfDocument pdfDoc = new com.itextpdf.kernel.pdf.PdfDocument(writer);
        Document document = new Document(pdfDoc);

        for (var treatment : medicalRecord) {
            document.add(new Paragraph("Fecha de consulta: " + treatment.date().toString()));
            document.add(new Paragraph("Práctica médica: " + treatment.medicalProcedureCode().toString()));
            document.add(new Paragraph("Patología/s: " + treatment.pathologyList().toString()));
            document.add(new Paragraph("Medicamentos asociados: " + treatment.medicineList().toString()));
            document.add(new Paragraph("Estado del tratamiento: " +treatment.treatmentStatus()));
            document.add(new Paragraph("Profesional a cargo: " + treatment.doctorId()));
            document.add(new Paragraph(" "));
        }

        document.close();

        ByteArrayInputStream bis = new ByteArrayInputStream(out.toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=medicalRecord.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}


