package io.promoflow.api.dto.promotion.response

import io.promoflow.infrastructure.persistance.entity.promotion.Promotion
import java.util.UUID

data class PromotionIdResponse(
    val id: UUID?
) {
    companion object {
        fun form(entity: Promotion): PromotionIdResponse = PromotionIdResponse(
            id = entity.id
        )
    }
}
