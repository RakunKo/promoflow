package io.promoflow.api.dto.promotion.response

import io.promoflow.infrastructure.persistance.entity.promotion.Promotion
import io.promoflow.infrastructure.persistance.entity.promotion.PromotionStatus
import java.time.Instant
import java.util.*

data class GetPromotionsResponse(
    val promotions: List<GetPromotionDetailResponse>
) {
    data class GetPromotionDetailResponse(
        val id: UUID,
        val userId: UUID,
        val name: String,
        val startTime: Instant,
        val endTime: Instant,
        val status: PromotionStatus,
        val createdAt: Instant,
        val updatedAt: Instant,
    ) {
        companion object {
            fun from(entity: Promotion): GetPromotionDetailResponse = GetPromotionDetailResponse(
                id = entity.id,
                userId = entity.user.id,
                name = entity.name,
                startTime = entity.startTime,
                endTime = entity.endTime,
                status = entity.status,
                createdAt = entity.createdAt,
                updatedAt = entity.updatedAt
            )
        }
    }

    companion object {
        fun of(entity: List<Promotion>) = GetPromotionsResponse (
            promotions = entity.map { GetPromotionDetailResponse.from(it) }.toList()
        )
    }
}

