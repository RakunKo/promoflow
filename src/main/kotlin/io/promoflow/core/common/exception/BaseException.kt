package io.promoflow.core.common.exception

import io.promoflow.core.common.code.BaseErrorCode
import io.promoflow.core.common.utils.formatErrorMessage
import io.promoflow.api.dto.ErrorResponse

abstract class BaseException(
    private val err: BaseErrorCode,
    private vararg val args: Any,
) : RuntimeException() {
    val status = err.status

    fun toResponse(): ErrorResponse = ErrorResponse(
        status = err.status.value(),
        message = err.message.formatErrorMessage(*args)
    )
}
