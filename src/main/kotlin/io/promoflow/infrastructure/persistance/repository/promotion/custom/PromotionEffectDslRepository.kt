package io.promoflow.infrastructure.persistance.repository.promotion.custom

import io.promoflow.infrastructure.persistance.entity.promotion.Promotion
import io.promoflow.infrastructure.persistance.entity.promotion.PromotionRule
import io.promoflow.infrastructure.persistance.entity.promotion.PromotionStatus
import java.time.LocalDateTime
import java.util.*

interface PromotionEffectDslRepository {
    fun deletePromotionEffects(
        promotionEffectIds: List<UUID>
    ): Long
}