package io.eatda.infrastructure.persistance.repository

import io.eatda.infrastructure.persistance.entity.user.JwtKey
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.Instant
import java.util.UUID

@Repository
interface KeyRepository : JpaRepository<JwtKey, UUID> {
    fun findJwtKeysByActiveIsTrue(): List<JwtKey>
    fun deleteAllByActiveIsFalseAndCreatedAtBefore(cutoff: Instant)
}
