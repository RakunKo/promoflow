package io.eatda.api.dto.promotion.params

import io.eatda.api.dto.common.Pagination
import io.eatda.infrastructure.persistance.entity.promotion.PromotionStatus
import java.time.Instant

data class PromotionParams(
    val status: PromotionStatus?,
    val startTime: Instant?,
    val endTime: Instant?
): Pagination()
