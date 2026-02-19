package io.promoflow.core.common.exception

import io.promoflow.core.common.code.BaseErrorCode

class ApiException(
    errorCode: BaseErrorCode,
    vararg args: Any,
) : BaseException(errorCode, *args)

class UnauthorizedException(
    errorCode: BaseErrorCode,
    vararg args: Any,
) : BaseException(errorCode, *args)

class InfraException(
    errorCode: BaseErrorCode,
    vararg args: Any,
) : BaseException(errorCode, *args)