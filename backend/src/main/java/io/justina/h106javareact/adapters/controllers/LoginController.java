package io.justina.h106javareact.adapters.controllers;

import io.justina.h106javareact.adapters.dtos.login.RequestLogin;
import io.justina.h106javareact.adapters.dtos.login.ResponseLogin;
import io.justina.h106javareact.adapters.dtos.login.UpdateDtoPassword;
import io.justina.h106javareact.application.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("unused")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/login")
@SecurityRequirement(name = "Bearer Authentication")
public class LoginController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ResponseLogin> login(
            @RequestBody @Valid @NotNull RequestLogin requestLogin){
        return ResponseEntity.status(HttpStatus.OK).body(
                this.userService.login(requestLogin));
    }

    @PutMapping("/update/password")
    public ResponseEntity<Boolean> updatePatient(
            @RequestBody @Valid UpdateDtoPassword updateDtoPassword){
        return ResponseEntity.ok(userService.updatePassword(updateDtoPassword));
    }
}
