package io.justina.h106javareact.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String code;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Pharmacy> pharmacyList;
    private String description;
    boolean active;
}