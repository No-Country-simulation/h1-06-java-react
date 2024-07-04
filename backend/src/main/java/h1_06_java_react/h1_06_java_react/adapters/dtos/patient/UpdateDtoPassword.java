package h1_06_java_react.h1_06_java_react.adapters.dtos.patient;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateDtoPassword(
    @NotNull(message = "El Id no puede ser nulo")
    String id,
    @NotNull(message = "La contraseña no puede ser nulo")
    @Size(min = 7, message = "La contraseña debe tener al menos 8 carateres.")
    String password
) {
}
