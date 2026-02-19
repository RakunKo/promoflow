package io.eatda.api.dto.user

import io.eatda.infrastructure.persistance.entity.user.User
import java.util.UUID

data class UserIdResponse(
    val userId: UUID?
) {
    companion object{
        fun form(entity: User): UserIdResponse = UserIdResponse(
            userId = entity.id
        )
    }

}
