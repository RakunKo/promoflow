package io.promoflow.infrastructure.persistance.repository

import io.promoflow.infrastructure.persistance.entity.user.JwtKey
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
interface KeyRepository : JpaRepository<JwtKey, Long> {
    fun findJwtKeysByActiveIsTrue(): List<JwtKey>
    fun deleteAllByActiveIsFalseAndCreatedAtBefore(cutoff: Instant)
}
