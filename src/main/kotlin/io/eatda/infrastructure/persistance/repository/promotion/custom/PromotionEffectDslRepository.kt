package io.eatda.infrastructure.persistance.repository.promotion.custom

import java.util.*

interface PromotionEffectDslRepository {
    fun deletePromotionEffects(
        promotionEffectIds: List<UUID>
    ): Long
}