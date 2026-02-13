package io.promoflow.api.dto.promotion.request

import io.promoflow.api.dto.promotion.sub.ConditionDto
import io.promoflow.api.dto.promotion.sub.EffectDto
import io.promoflow.core.validator.annotations.NotSpecial
import jakarta.validation.Valid
import jakarta.validation.constraints.*

data class CreatePromotionRuleRequest(
    @field:NotBlank(message = "Promotion rule name must not be blank")
    @field:Size(max = 50, message = "Promotion rule name must be at most 50 characters")
    @field:NotSpecial(message = "Promotion rule name must not contain special characters")
    val name: String,

    @field:NotNull(message = "Promotion rule's priority must not be null")
    val priority: Int,

    @field:Valid
    val conditions: List<ConditionDto> = emptyList(),

    @field:Valid
    val effects: List<EffectDto> = emptyList(),
)