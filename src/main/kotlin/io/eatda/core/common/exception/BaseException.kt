package io.eatda.core.common.exception

import io.eatda.core.common.code.BaseErrorCode
import io.eatda.core.common.utils.formatErrorMessage
import io.eatda.api.dto.ErrorResponse

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
