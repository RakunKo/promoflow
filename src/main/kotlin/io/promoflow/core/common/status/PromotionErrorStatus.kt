package io.promoflow.core.common.status

import io.promoflow.core.common.code.BaseErrorCode
import org.springframework.http.HttpStatus

enum class PromotionErrorStatus(
    override val status: HttpStatus,
    override val message: String,
) : BaseErrorCode {
    PROMOTION_IS_NOT_EXIST(HttpStatus.NOT_FOUND, "Promotion %s is not found"),
    PROMOTION_DATE_INVALID(HttpStatus.BAD_REQUEST, "Promotion start, end date is invalid"),

    ;

}
