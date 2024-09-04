package com.team02.best_properta.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Best Properta API", version = "1.0.0", description = "API for Best Properta application"),
        security = @SecurityRequirement(name = "Bearer")
)
@SecurityScheme(name = "Bearer", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
public class OpenAPIConfig {
    // Swagger UI erişim adresi: http://localhost:8080/swagger-ui/index.html
}
