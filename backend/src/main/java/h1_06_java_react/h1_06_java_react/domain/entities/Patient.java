package h1_06_java_react.h1_06_java_react.domain.entities;

import h1_06_java_react.h1_06_java_react.domain.entities.enums.BloodType;
import h1_06_java_react.h1_06_java_react.domain.entities.enums.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String entityId;
    String healthcareProviderId;
    String doctorStaffId;
    String documentTypeId;

    String name;
    String surname;
    Long dni;
    LocalDate dateOfBirth;
    Gender gender;
    BloodType bloodType;
    
}
