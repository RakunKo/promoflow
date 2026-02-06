package io.promoflow.infrastructure.persistance.repository

import io.promoflow.infrastructure.persistance.entity.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserRepository: JpaRepository<User, UUID> {
    fun existsUserByName(name: String): Boolean
    fun findUserByProviderId(providerId: String): User?
}