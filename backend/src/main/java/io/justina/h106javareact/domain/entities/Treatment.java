package io.justina.h106javareact.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.justina.h106javareact.domain.entities.enums.TreatmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String medicalProcedureCode;
    @Column(columnDefinition="TEXT")
    private String medicalProcedureName;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Pathology> pathologyList;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Medicine> medicineList;
    private String frequency; //TODO. Change it to an entity when coding reminder feature!
    private String dosage; //TODO. TreatmentData table with medId, freq, dosage and adminDetails...
    private String administrationDetails;
    private String doctorId;
    private TreatmentStatus treatmentStatus; //It replaces active property.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    @JsonBackReference
    private User patient;
    private LocalDateTime date = LocalDateTime.now();

}
