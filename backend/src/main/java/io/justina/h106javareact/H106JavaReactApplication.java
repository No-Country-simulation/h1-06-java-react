package io.justina.h106javareact;
// package h1_06_java_react.h1_06_java_react;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;

@SuppressWarnings("unused")
@SpringBootApplication
@SecurityScheme(name = "Bearer Authentication", scheme = "bearer",bearerFormat ="JWT" , type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class H106JavaReactApplication {

	public static void main(String[] args) {
		SpringApplication.run(H106JavaReactApplication.class, args);
	}

}
