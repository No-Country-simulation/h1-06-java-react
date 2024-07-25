package io.justina.h106javareact.domain.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.justina.h106javareact.domain.entities.enums.BloodType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientData {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Enumerated(EnumType.STRING)
    private BloodType bloodType;
    private String healthcareProviderId;
    private String socialSecurityNumber;
    private Boolean isDonor;
    private String relativeDataId;
    private Double weight;
    @OneToMany(mappedBy = "patientId", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Antigen> antigenList;
    @OneToMany(mappedBy = "patientId", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Antibody> antibodyList;
}
