package io.promoflow.api.dto.user

import io.promoflow.infrastructure.persistance.entity.user.User
import java.util.UUID

data class UserIdResponse(
    val userId: UUID?
) {
    fun form(entity: User): UserIdResponse = UserIdResponse(
        userId = entity.id
    )
}
