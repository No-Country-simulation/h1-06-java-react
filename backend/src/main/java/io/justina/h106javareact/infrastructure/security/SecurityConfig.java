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
            "/api/v1/login",
            "/api/v1/patient/register",
            "/api/v1/doctor/register",
            "/api/v1/relative/register",
            "/api/v1/doctor/specialties",
            "/api/v1/email/emailValidation/{token}/{email}",
            "/api/v1/login/password/{email}",
            // MOVE THESE THREE TO AUTH ENDPOINTS!
            "/api/v1/pathology",
            "/api/v1/medicine",
            "/api/v1/medicalProcedure",
            //MEDICINE AND PATHOLOGY SCRAPING, SHOULD NOT BE FREE IN PROD! USE ONCE ONLY!
            "/api/v1/medicine/scraping",
            "/api/v1/pathology/scraping",
            "/api/v1/medicalProcedure/scraping"
    };

    private static final String[] AUTH_ENDPOINTS = {
            // REGARDING PATIENT
            "/api/v1/patient/id/{id}/{active}",
            "/api/v1/patient/email/{email}/{active}",
            // REGARDING DOCTOR
            "/api/v1/doctor/id/{id}/{active}",
            "/api/v1/doctor/email/{email}/{active}",
            // REGARDING RELATIVE
            "/api/v1/relative/id/{id}/{active}",
            "/api/v1/relative/email/{email}/{active}",
            // REGARDING TREATMENT
            "/api/v1/treatment/id/{id}",
            "/api/v1/treatment/medicalProcedureCode/{code}",
            "/api/v1/treatment/medicalProcedureName/{name}",
            "/api/v1/treatment/patient/{id}",
            "/api/v1/treatment/doctor/{id}",
            "/api/v1/treatment/date/{date}",
            "/api/v1/treatment/status/{status}",
            "/api/v1/treatment/pathology/{code}",
            "/api/v1/treatment/medicine/{code}",
            //REGARDING APPOINTMENT
            "api/v1/appointment/{id}/{active}",
            "api/v1/appointment/patient/{id}/{active}",
            "api/v1/appointment/patient/{id}/{date}/{active}",
            "api/v1/appointment/patient/{id}/{startDate}/{endDate}/{active}"

    };

    private static final String[] PATIENT_ENDPOINTS = {
            // REGARDING SELF
            "/api/v1/patient/update",
            "/api/v1/patient/toggle/{id}",
            "/api/v1/appointment/create",
            "/api/v1/treatment/patient/{id}/medicalRecordPdf",
            "api/v1/specialty/{specialty}",
            "api/v1/doctor/{id}/{date}/available",
            "/api/v1/appointment/toggle/{id}"
    };

    private static final String[] DOCTOR_ENDPOINTS = {
            // REGARDING SELF
            "/api/v1/doctor/update",
            "/api/v1/doctor/toggle/{id}",
            "/api/v1/treatment/create",
            "/api/v1/treatment/update",
            //REGARDING THEIR APPOINTMENTS
            "api/v1/appointment/doctor/{id}/{active}",
            "api/v1/appointment/doctor/{id}/{date}/{active}",
            "api/v1/appointment/doctor/{id}/{startDate}/{endDate}/{active}",
            "api/v1/appointment/cancel/{doctorId}",
            "api/v1/patient/surname/{surname}/{active}",
            //REGARDING PATIENT INFO
            "api/v1/patient/medicalInformation"
    };

    private static final String[] RELATIVE_ENDPOINTS = {
            // REGARDING SELF
            "/api/v1/relative/update",
            "/api/v1/relative/toggle/{id}"
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
                                .anyRequest().authenticated()
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
