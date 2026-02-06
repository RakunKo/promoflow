package io.promoflow.api.dto.user

import io.promoflow.core.validator.annotations.NotSpecial
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UpdateUsernameRequest(
    @field:NotBlank(message = "Name must not be blank")
    @field:Size(max = 20, message = "Name must be at most 20 characters")
    @field:NotSpecial(message = "Name must not contain special characters")
    val name: String,
)
