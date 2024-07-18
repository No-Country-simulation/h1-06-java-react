package io.justina.h106javareact.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.justina.h106javareact.domain.entities.enums.TreatmentStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    @JsonBackReference
    private User doctor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "relative_id")
    @JsonBackReference
    private User relative;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    @JsonBackReference
    private User patient;

    private LocalDateTime date = LocalDateTime.now().withSecond(0).withNano(0);

    private String observations;

    private Boolean active;

}