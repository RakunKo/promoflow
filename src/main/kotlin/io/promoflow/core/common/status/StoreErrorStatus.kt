package io.promoflow.core.common.status

import io.promoflow.core.common.code.BaseErrorCode
import org.springframework.http.HttpStatus

enum class StoreErrorStatus(
    override val status: HttpStatus,
    override val message: String,
) : BaseErrorCode {
    STORE_IS_NOT_EXIST(HttpStatus.NOT_FOUND, "Store is not found"),

    ;

}
