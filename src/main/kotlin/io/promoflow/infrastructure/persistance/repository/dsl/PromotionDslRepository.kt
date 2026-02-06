package io.promoflow.infrastructure.persistance.repository.dsl

import io.promoflow.infrastructure.persistance.entity.promotion.Promotion
import io.promoflow.infrastructure.persistance.entity.promotion.PromotionStatus
import java.time.LocalDateTime

interface PromotionDslRepository {
    fun searchPromotion(status: PromotionStatus?,
                        startTime: LocalDateTime?,
                        endTime: LocalDateTime?,
                        page: Int,
                        size: Int
    ): List<Promotion>
}