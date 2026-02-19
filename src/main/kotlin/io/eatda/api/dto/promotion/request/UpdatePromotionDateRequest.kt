package io.eatda.api.dto.promotion.request

import java.time.Instant

data class UpdatePromotionDateRequest(
    val startTime: Instant,
    val endTime: Instant,
)
