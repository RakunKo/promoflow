package io.eatda.core.common.status

import io.eatda.core.common.code.BaseErrorCode
import org.springframework.http.HttpStatus

enum class PromotionErrorStatus(
    override val status: HttpStatus,
    override val message: String,
) : BaseErrorCode {
    PROMOTION_IS_NOT_EXIST(HttpStatus.NOT_FOUND, "Promotion %s is not found"),
    PROMOTION_DATE_INVALID(HttpStatus.BAD_REQUEST, "Promotion start, end date is invalid"),
    PROMOTION_CREATOR_NOT_SAME(HttpStatus.CONFLICT, "Promotion can be modified by creator"),
    PROMOTION_STATUS_CONFLICT(HttpStatus.CONFLICT, "Promotion status cannot be modified in its current state."),

    PROMOTION_RULE_IS_NOT_EXIST(HttpStatus.NOT_FOUND, "Promotion rule %s is not found"),
    PROMOTION_RULE_IS_EXCEEDED(HttpStatus.BAD_REQUEST, "Maximum number of promotion rules has been exceeded"),


    ;

}
