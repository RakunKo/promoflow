package io.eatda.api.dto.promotion.request

import io.eatda.core.validator.annotations.NotSpecial
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UpdatePromotionNameRequest(
    @field:NotBlank(message = "Promotion name must not be blank")
    @field:Size(max = 50, message = "Promotion name must be at most 50 characters")
    @field:NotSpecial(message = "Promotion name must not contain special characters")
    val name: String,
)
