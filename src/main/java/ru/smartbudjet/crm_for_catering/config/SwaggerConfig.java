package ru.smartbudjet.crm_for_catering.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "backend_crm_for_catering",
                version = "1.0",
                description = "API "
        ),
        security = {
                @SecurityRequirement(
                        name = "jwtBearer"
                )
        }
)
@SecurityScheme(
        name = "jwtBearer",
        description = "Сюда кидать JWTToken",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER,
        paramName = "Authorization"
)
@Configuration
public class SwaggerConfig {
}
