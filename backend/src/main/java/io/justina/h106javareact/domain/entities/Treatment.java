package io.justina.h106javareact.domain.entities;

import io.justina.h106javareact.domain.entities.enums.TreatmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String code;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Medicine> medicineList;
    private String frequency; //TODO. Change it to an entity when coding reminder feature!
    private String dosage;
    private String administrationDetails;
    private String doctorId;
    private TreatmentStatus treatmentStatus; //It replaces active property.

}
