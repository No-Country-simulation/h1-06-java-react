package io.justina.h106javareact.infrastructure.security;

import io.justina.h106javareact.adapters.repositories.UserRepository;
import io.justina.h106javareact.domain.entities.enums.Role;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        var found = userRepository.findByEmail(email);
        if (found.isPresent()) {
            var user = User.builder()
                    .username(email)
                    .roles(Role.PACIENTE.name())
                    .password(found.get().getPassword())
                    .build();
            return user;
        }

        throw new EntityNotFoundException(email);
    }
}