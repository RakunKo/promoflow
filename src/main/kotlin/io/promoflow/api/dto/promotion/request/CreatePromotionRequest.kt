package io.promoflow.api.dto.promotion.request

import io.promoflow.core.validator.annotations.NotSpecial
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

data class CreatePromotionRequest(
    @field:NotBlank(message = "Promotion name must not be blank")
    @field:Size(max = 50, message = "Promotion name must be at most 50 characters")
    @field:NotSpecial(message = "Promotion name must not contain special characters")
    val name: String,

    @field:NotNull(message = "Start time is required")
    val startTime: LocalDateTime,

    @field:NotNull(message = "End time is required")
    val endTime: LocalDateTime,
)
