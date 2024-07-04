package h1_06_java_react.h1_06_java_react.adapters.dtos.login;

public record ResponseLogin(
        String jwt,
        String role,
        String id,
        String name,
        String surname
) {
}
