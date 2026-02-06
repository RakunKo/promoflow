package io.promoflow.api.dto.promotion.request

import io.promoflow.api.dto.promotion.sub.ConditionRequest
import io.promoflow.api.dto.promotion.sub.EffectRequest
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull

data class UpdatePromotionRuleRequest(
    @field:NotNull(message = "Promotion rule's priority must not be null")
    val priority: Int,

    @field:Valid
    val conditions: List<ConditionRequest> = emptyList(),

    @field:Valid
    @field:NotNull(message = "Promotion effect must be defined")
    val effect: EffectRequest
)
