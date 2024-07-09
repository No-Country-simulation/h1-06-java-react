package io.justina.h106javareact.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    private static final String[] FREE_ENDPOINTS = {
            // -- Swagger UI v3 (OpenAPI)
            "/api/v1/company/create",
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            // -- REGISTER & LOGIN
            "/login",
            "/patient/register",
            "/doctor/register",
            "/relative/register",
            "/doctor/specialties"
    };

    private static final String[] AUTH_ENDPOINTS = {
            // REGARDING PATIENT
            "patient/id/{id}/{active}",
            "patient/email/{email}/{active}",
            // REGARDING DOCTOR
            "doctor/id/{id}/{active}",
            "doctor/email/{email}/{active}",
            // REGARDING RELATIVE
            "relative/id/{id}/{active}",
            "relative/email/{email}/{active}"
    };

    private static final String[] PATIENT_ENDPOINTS = {
            // REGARDING SELF
            "patient/update",
            "patient/toggle/{id}"
    };

    private static final String[] DOCTOR_ENDPOINTS = {
            // REGARDING SELF
            "doctor/update",
            "doctor/toggle/{id}"
    };

    private static final String[] RELATIVE_ENDPOINTS = {
            // REGARDING SELF
            "relative/update",
            "relative/toggle/{id}"
    };


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable).cors((cors) -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(request ->
                        request.requestMatchers(FREE_ENDPOINTS).permitAll()
                                .requestMatchers(AUTH_ENDPOINTS).hasAnyRole("DOCTOR", "PACIENTE", "TUTOR")
                                .requestMatchers(PATIENT_ENDPOINTS).hasAnyRole("PACIENTE", "TUTOR")
                                .requestMatchers(DOCTOR_ENDPOINTS).hasRole("DOCTOR")
                                .requestMatchers(RELATIVE_ENDPOINTS).hasRole("TUTOR")
                                .anyRequest().permitAll()
                )
                .sessionManagement(sessionManager ->
                        sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
