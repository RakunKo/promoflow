package io.promoflow.infrastructure.security.jwt

import io.jsonwebtoken.Jwts
import io.promoflow.infrastructure.persistance.entity.user.JwtKey
import io.promoflow.infrastructure.persistance.entity.user.User
import io.promoflow.infrastructure.security.key.KeyManager
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.stereotype.Component
import java.security.Key
import java.security.KeyFactory
import java.security.interfaces.RSAPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.time.Duration
import java.time.Instant
import java.util.*

@Component
class JwtProvider(
    private val keyManager: KeyManager,
) {
    private var cachedJwtKey: JwtKey? = null
    private var keyLoadedAt: Instant? = null
    private val cacheExpiry = Duration.ofHours(1)

    fun generateJwtToken(
        subject: String,
        user: User,
    ): JwtToken =
        JwtToken(
            accessToken = generateToken(subject, user, 3600),
            refreshToken = generateToken(subject, user, 604800),
        )

    fun getJwtDecoder(): JwtDecoder {
        val publicKey = getKey().publicKey.toPublicKey()
        return NimbusJwtDecoder.withPublicKey(publicKey as RSAPublicKey).build()
    }

    private fun generateToken(
        subject: String,
        user: User,
        expiration: Long,
    ): String {
        val now = Instant.now()

        return Jwts
            .builder()
            .subject(subject)
            .apply {
                claim("id", user.id)
                claim("status", user.activeStatus)
            }.issuedAt(Date.from(now))
            .expiration(Date.from(now.plusSeconds(expiration)))
            .signWith(getKey().privateKey.toPrivateKey())
            .compact()
    }

    private fun getKey(): JwtKey {
        val now = Instant.now()
        if (cachedJwtKey == null ||
            keyLoadedAt == null ||
            Duration.between(keyLoadedAt, now) > cacheExpiry
        ) {
            cachedJwtKey = keyManager.getActiveKeys().first()
            keyLoadedAt = now
        }
        return cachedJwtKey!!
    }

    private fun ByteArray.toPrivateKey(): Key {
        val keySpec = PKCS8EncodedKeySpec(this)
        val keyFactory = KeyFactory.getInstance("RSA")
        return keyFactory.generatePrivate(keySpec)
    }

    private fun ByteArray.toPublicKey(): Key {
        val keySpec = X509EncodedKeySpec(this)
        val keyFactory = KeyFactory.getInstance("RSA")
        return keyFactory.generatePublic(keySpec)
    }
}
