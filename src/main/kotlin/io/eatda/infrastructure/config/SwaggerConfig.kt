package io.eatda.infrastructure.config

import io.eatda.infrastructure.security.annotations.AuthUser
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springdoc.core.utils.SpringDocUtils
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    init {
        SpringDocUtils.getConfig().addAnnotationsToIgnore(AuthUser::class.java)
    }

    @Bean
    fun apiDocs(): OpenAPI =
        OpenAPI()
            .info(
                Info()
                    .title("Nexio Api Docs")
                    .version("v0.0.1")
                    .description("Nexio API documentation using OpenAPI 3"),
            )
            .addSecurityItem(SecurityRequirement().addList("BearerAuth"))
            .components(
                Components().addSecuritySchemes(
                    "BearerAuth",
                    SecurityScheme()
                        .name("BearerAuth")
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                )
            )
}
