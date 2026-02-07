package io.promoflow.infrastructure.persistance.repository.dsl

import io.promoflow.infrastructure.persistance.entity.promotion.Promotion
import io.promoflow.infrastructure.persistance.entity.promotion.PromotionRule
import io.promoflow.infrastructure.persistance.entity.promotion.PromotionStatus
import java.time.LocalDateTime
import java.util.*

interface PromotionConditionDslRepository {
    fun deletePromotionConditions(
        promotionConditionIds: List<UUID>
    ): Long
}