package io.promoflow.infrastructure.security

import io.promoflow.core.common.code.BaseErrorCode
import io.promoflow.core.common.status.CommonErrorStatus
import io.promoflow.core.common.utils.JsonUtils
import io.promoflow.core.service.UserIdentifier
import io.promoflow.core.service.UserService
import io.promoflow.infrastructure.security.jwt.JwtProvider
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.security.oauth2.core.user.OAuth2User

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtProvider: JwtProvider,
    private val userService: UserService,
) {
    companion object {
        private val PUBLIC_PATHS =
            arrayOf(
                "/login/**",
                "/oauth2/**",
                "/swagger-ui.html",
                "/swagger-ui/**",
                "/v3/api-docs/**",
                "/webjars/**",
                "/api/v1/users/signup"
            )
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .csrf { it.disable() }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers(*PUBLIC_PATHS).permitAll()
                    .requestMatchers("/api/v1/**").authenticated()
                    .anyRequest().authenticated()
            }
            .oauth2Login { oauth2 ->
                oauth2
                    .successHandler(oauthSuccessHandler())
                    .failureHandler(oauthFailureHandler())
            }
            .oauth2ResourceServer { oauth2 ->
                oauth2.jwt { jwt ->
                    jwt.decoder(jwtProvider.getJwtDecoder())
                }
            }
            .exceptionHandling { ex ->
                ex
                    .authenticationEntryPoint(unauthorizedEntryPoint())
                    .accessDeniedHandler(forbiddenHandler())
            }
            .build()

    private fun oauthSuccessHandler() = AuthenticationSuccessHandler { request, response, authentication ->
        val oauthToken = authentication as OAuth2AuthenticationToken
        val user = oauthToken.principal as OAuth2User
        val subject = user.getAttribute<String>("sub") ?: user.name

        try {
            val foundUser = userService.getUser(UserIdentifier.ByProvider(subject))
            val jwt = jwtProvider.generateJwtToken(subject, foundUser)

            val responseBody = mapOf(
                "accessToken" to jwt.accessToken,
                "refreshToken" to jwt.refreshToken,
                "tokenType" to "Bearer",
                "expiresIn" to 3600,
            )

            response.writeJsonResponse(responseBody, 200)
        } catch (e: Exception) {
            response.writeErrorResponse(CommonErrorStatus.UNAUTHORIZED)
        }
    }

    private fun oauthFailureHandler() = AuthenticationFailureHandler { _, response, _ ->
        response.writeErrorResponse(CommonErrorStatus.UNAUTHORIZED)
    }

    private fun unauthorizedEntryPoint() = AuthenticationEntryPoint { _, response, _ ->
        response.writeErrorResponse(CommonErrorStatus.UNAUTHORIZED)
    }

    private fun forbiddenHandler() = AccessDeniedHandler { _, response, _ ->
        response.writeErrorResponse(CommonErrorStatus.FORBIDDEN_ROLE)
    }

    private fun HttpServletResponse.writeJsonResponse(body: Any, status: Int) {
        this.status = status
        this.contentType = MediaType.APPLICATION_JSON_VALUE
        this.writer.write(JsonUtils.objectMapper.writeValueAsString(body))
        this.writer.flush()
    }

    private fun HttpServletResponse.writeErrorResponse(errorCode: BaseErrorCode) {
        val body = mapOf(
            "status" to errorCode.status.value(),
            "message" to errorCode.message,
        )
        writeJsonResponse(body, errorCode.status.value())
    }
}