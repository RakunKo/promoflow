package io.eatda.api.dto.waiting.request

import jakarta.validation.constraints.Min

data class CreateWaitingRequest(
    @field:Min(value = 1, message = "Waiting party size must be bigger than 1")
    val partySize: Int,
)
