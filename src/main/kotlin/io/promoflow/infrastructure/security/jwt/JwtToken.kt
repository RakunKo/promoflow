package io.promoflow.infrastructure.security.jwt

data class JwtToken(
    val accessToken: String,
    val refreshToken: String,
)
