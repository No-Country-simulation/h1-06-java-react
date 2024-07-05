package io.justina.h106javareact.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lab {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private int description;
    private boolean active;
}
