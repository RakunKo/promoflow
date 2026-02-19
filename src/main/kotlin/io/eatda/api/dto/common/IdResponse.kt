package io.eatda.api.dto.common

import java.util.UUID

data class IdResponse(
    val id: UUID?
) {
    companion object {
        fun of(id: UUID): IdResponse = IdResponse(
            id = id
        )
    }
}
