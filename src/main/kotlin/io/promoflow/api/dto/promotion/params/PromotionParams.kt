package io.promoflow.api.dto.promotion.params

import io.promoflow.api.dto.common.Pagination
import io.promoflow.infrastructure.persistance.entity.promotion.PromotionStatus
import java.time.LocalDateTime

data class PromotionParams(
    val status: PromotionStatus?,
    val startTime: LocalDateTime?,
    val endTime: LocalDateTime?
): Pagination()
