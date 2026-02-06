package io.promoflow.api.dto.common

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min

abstract class Pagination (
    @field:Min(0)
    val page: Int = 0,

    @field:Min(5)
    @field:Max(50)
    val size: Int = 10
)