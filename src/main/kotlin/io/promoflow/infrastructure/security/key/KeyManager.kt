package io.promoflow.infrastructure.security.key

import io.promoflow.infrastructure.persistance.entity.user.JwtKey
import io.promoflow.infrastructure.persistance.repository.KeyRepository
import org.springframework.stereotype.Component
import java.security.KeyPairGenerator
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.time.Instant
import java.time.temporal.ChronoUnit

@Component
class KeyManager(
    private val keyRepository: KeyRepository,
) {
    fun getActiveKeys(): List<JwtKey> {
        val keys = keyRepository.findJwtKeysByActiveIsTrue()

        return keys.ifEmpty {
            listOf(rotateKey())
        }
    }

    fun rotateKey(): JwtKey {
        val keyPairGen = KeyPairGenerator.getInstance("RSA").apply { initialize(2048) }
        val keyPair = keyPairGen.generateKeyPair()

        val jwtKey =
            JwtKey(
                privateKey = (keyPair.private as RSAPrivateKey).encoded,
                publicKey = (keyPair.public as RSAPublicKey).encoded,
                active = true,
            )

        return keyRepository.save(jwtKey)
    }

    fun cleanupOldKeys() {
        val cutoff = Instant.now().minus(30, ChronoUnit.DAYS)
        keyRepository.deleteAllByActiveIsFalseAndCreatedAtBefore(cutoff)
    }
}
