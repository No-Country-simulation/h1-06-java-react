package io.justina.h106javareact.application.validations;

import io.justina.h106javareact.adapters.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SelfValidation {

    private final UserRepository userRepository;

    public boolean checkSelfValidation(String id) {
        String userLogged = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userRepository.findById(id);
        if (user.isEmpty() || !userLogged.equals(user.get().getEmail())) {
            throw new IllegalArgumentException("¡El usuario no tiene permiso para esta acción!");
        }
        return true;
    }
}
