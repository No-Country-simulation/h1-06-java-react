package h1_06_java_react.h1_06_java_react.adapters.implementations;

import h1_06_java_react.h1_06_java_react.adapters.dtos.login.RequestLogin;
import h1_06_java_react.h1_06_java_react.adapters.dtos.login.ResponseLogin;
import h1_06_java_react.h1_06_java_react.adapters.repositories.PatientRepository;
import h1_06_java_react.h1_06_java_react.application.services.AuthenticationService;
import h1_06_java_react.h1_06_java_react.infrastructure.security.JwtService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PatientRepository patientRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public ResponseLogin login(RequestLogin data) {

        String role = "";
        String id = "";
        String token = "";
        String name = "";
        String surname = "";

        var patient = patientRepository.findByEmail(data.email());
        if (patient.isPresent()) {
            role = patient.get().getAuthorities().toString();
            id = patient.get().getId();
            token = jwtService.getToken(patient.get());
            name = patient.get().getName();
            surname = patient.get().getSurname();
        } else throw new EntityNotFoundException("No se puede encontrar el usuario con el email " + data.email());


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(data.email(),
                        data.password()));

        ResponseLogin responseLogin = new ResponseLogin(token, role, id, name, surname);

        return responseLogin;
    }

}
