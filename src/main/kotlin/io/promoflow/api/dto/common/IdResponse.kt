package io.promoflow.api.dto.common

import io.promoflow.infrastructure.persistance.entity.promotion.Promotion
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
