package h1_06_java_react.h1_06_java_react.domain.entities;

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
public class Pathology {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String specialtyId;
    String description;
}
