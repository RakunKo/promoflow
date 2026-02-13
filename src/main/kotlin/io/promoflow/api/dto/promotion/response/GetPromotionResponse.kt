package io.promoflow.api.dto.promotion.response

import io.promoflow.infrastructure.persistance.entity.promotion.Promotion
import io.promoflow.infrastructure.persistance.entity.promotion.PromotionRule
import io.promoflow.infrastructure.persistance.entity.promotion.PromotionStatus
import java.time.Instant
import java.util.*

data class GetPromotionResponse(
    val id: UUID,
    val userId: UUID,
    val name: String,
    val startTime: Instant,
    val endTime: Instant,
    val status: PromotionStatus,
    val rules: List<PromotionRuleResponse>,
    val createdAt: Instant,
    val updatedAt: Instant,
) {
    data class PromotionRuleResponse(
        val id: UUID,
        val name: String,
        val priority: Int
    ) {
        companion object {
            fun of(entity: PromotionRule) = PromotionRuleResponse(
                id = entity.id,
                name = entity.name,
                priority = entity.priority
            )
        }
    }

    companion object {
        fun of(entity: Promotion) = GetPromotionResponse(
            id = entity.id,
            userId = entity.user.id,
            name = entity.name,
            startTime = entity.startTime,
            endTime = entity.endTime,
            status = entity.status,
            rules = entity.rules.map { PromotionRuleResponse.of(it) }.toList(),
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt
        )

    }
}