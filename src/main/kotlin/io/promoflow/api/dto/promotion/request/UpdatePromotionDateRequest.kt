package io.promoflow.api.dto.promotion.request

import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

data class UpdatePromotionDateRequest(
    @field:NotNull(message = "Start time is required")
    val startTime: LocalDateTime,

    @field:NotNull(message = "End time is required")
    val endTime: LocalDateTime,
)
