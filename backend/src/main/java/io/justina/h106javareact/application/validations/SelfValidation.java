package io.justina.h106javareact.application.validations;

import io.justina.h106javareact.adapters.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SelfValidation {

    private final PatientRepository patientRepository;

    public boolean checkSelfValidation(String id) {
        String userLogged = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = patientRepository.findById(id);
        if (user.isEmpty() || !userLogged.equals(user.get().getEmail())) {
            throw new IllegalArgumentException("¡El usuario no tiene permiso para esta acción!");
        }
        return true;
    }
}
