package com.example.usermanagementsystem.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Dido",
                        email = "didonikolov01@gmail.com"
                ),
                description = "OpenApi documentation",
                title = "OpenApi specification - Dido",
                version = "1.0"
        ),
        servers = {
               @Server(
                       description = "Local ENV",
                       url = "http://localhost:8080"
               )
        },
        security = {
                @SecurityRequirement(
                        name = "basicAuth"
                )
        }
)
@SecurityScheme(
        name = "basicAuth",
        description = "Basic Auth description",
        scheme = "basic",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
