package io.eatda.infrastructure.persistance.repository.promotion.custom

import java.util.*

interface PromotionConditionDslRepository {
    fun deletePromotionConditions(
        promotionConditionIds: List<UUID>
    ): Long
}