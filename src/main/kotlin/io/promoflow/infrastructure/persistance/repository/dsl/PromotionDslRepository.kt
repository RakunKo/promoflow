package io.promoflow.infrastructure.persistance.repository.dsl

import io.promoflow.infrastructure.persistance.entity.promotion.Promotion
import io.promoflow.infrastructure.persistance.entity.promotion.PromotionStatus
import java.time.Instant

interface PromotionDslRepository {
    fun searchPromotion(status: PromotionStatus?,
                        startTime: Instant?,
                        endTime: Instant?,
                        page: Int,
                        size: Int
    ): List<Promotion>
}