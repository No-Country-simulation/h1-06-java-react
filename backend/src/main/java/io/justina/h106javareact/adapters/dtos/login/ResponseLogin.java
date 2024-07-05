package io.justina.h106javareact.adapters.dtos.login;

public record ResponseLogin(
        String jwt,
        String role,
        String id,
        String name,
        String surname
) {
}
