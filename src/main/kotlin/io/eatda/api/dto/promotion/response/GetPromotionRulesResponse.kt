package io.eatda.api.dto.promotion.response

import io.eatda.api.dto.promotion.sub.ConditionDto
import io.eatda.api.dto.promotion.sub.EffectDto
import io.eatda.infrastructure.persistance.entity.promotion.PromotionRule
import java.time.Instant
import java.util.*

data class GetPromotionRulesResponse(
    val id: UUID,
    val promotions: List<GetPromotionRuleDetailResponse>
) {
    data class GetPromotionRuleDetailResponse(
        val id: UUID,
        val name: String,
        val priority: Int,
        val conditions: List<ConditionDto>,
        val effects: List<EffectDto>,
        val createdAt: Instant,
        val updatedAt: Instant,
    ) {
        companion object {
            fun of(entity: PromotionRule) = GetPromotionRuleDetailResponse(
                id = entity.id,
                name = entity.name,
                conditions = entity.conditions.map { ConditionDto.fromEntity(it) },
                effects = entity.effects.map { EffectDto.fromEntity(it) },
                priority = entity.priority,
                createdAt = entity.createdAt,
                updatedAt = entity.updatedAt
            )
        }
    }

    companion object {
        fun of(id: UUID, entity: List<PromotionRule>) = GetPromotionRulesResponse (
            id = id,
            promotions = entity.map { GetPromotionRuleDetailResponse.of(it) }.toList()
        )
    }
}

