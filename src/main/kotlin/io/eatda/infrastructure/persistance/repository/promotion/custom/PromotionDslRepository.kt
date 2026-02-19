package io.eatda.infrastructure.persistance.repository.promotion.custom

import io.eatda.infrastructure.persistance.entity.promotion.Promotion
import io.eatda.infrastructure.persistance.entity.promotion.PromotionStatus
import java.time.Instant

interface PromotionDslRepository {
    fun searchPromotion(status: PromotionStatus?,
                        startTime: Instant?,
                        endTime: Instant?,
                        page: Int,
                        size: Int
    ): List<Promotion>
}