package io.justina.h106javareact.adapters.repositories;

import io.justina.h106javareact.domain.entities.DoctorData;
import io.justina.h106javareact.domain.entities.enums.MedicalLicense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorDataRepository extends JpaRepository<DoctorData, String> {
    DoctorData findByLicensePlaceAndMedicalLicense(MedicalLicense licensePlace, String medicalLicense);

}