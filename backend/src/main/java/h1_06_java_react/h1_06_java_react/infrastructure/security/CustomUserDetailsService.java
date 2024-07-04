package h1_06_java_react.h1_06_java_react.infrastructure.security;

import h1_06_java_react.h1_06_java_react.adapters.repositories.PatientRepository;
import h1_06_java_react.h1_06_java_react.domain.entities.enums.Role;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final PatientRepository patientRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        var found = patientRepository.findByEmail(email);
        if (found.isPresent()) {
            var user = User.builder()
                    .username(email)
                    .roles(Role.PATIENT.name())
                    .password(found.get().getPassword())
                    .build();
            return user;
        }

        throw new EntityNotFoundException(email);
    }
}