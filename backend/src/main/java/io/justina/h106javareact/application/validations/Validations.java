package io.justina.h106javareact.application.validations;

import io.justina.h106javareact.adapters.repositories.DoctorDataRepository;
import io.justina.h106javareact.adapters.repositories.UserRepository;
import io.justina.h106javareact.domain.entities.User;
import io.justina.h106javareact.domain.entities.enums.MedicalLicense;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Validations {

    private final UserRepository userRepository;
    private final DoctorDataRepository doctorDataRepository;

    public boolean checkSelfValidation(String id) {
        String userLogged = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userRepository.findById(id);
        if (user.isEmpty() || !userLogged.equals(user.get().getEmail())) {
            throw new IllegalArgumentException("¡El usuario no tiene permiso para esta acción!");
        }
        return true;
    }

    public User userAlreadyExistsValidation(String email) {
        var userAlreadyExists = userRepository.findByEmail(email);
        if (userAlreadyExists.isPresent() && userAlreadyExists.get().getActive()) {
            throw new EntityExistsException("¡Este email ya está en uso!");
        }
        if (userAlreadyExists.isPresent() && !userAlreadyExists.get().getActive()) {
            return userAlreadyExists.get();
        }
        return null;
    }

    public void licenseAlreadyExistValidation(MedicalLicense licensePlace, String medicalLicense) {
        var licenseAlreadyExists = doctorDataRepository.findByLicensePlaceAndMedicalLicense(
                licensePlace, medicalLicense);
        if (licenseAlreadyExists != null) {
            throw new EntityExistsException("¡Esta licencia médica corresponde a otro profesional!" +
                    " Corrobore número y tipo");
        }
    }


}
