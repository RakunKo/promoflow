package io.eatda.core.common.status

import io.eatda.core.common.code.BaseErrorCode
import org.springframework.http.HttpStatus

enum class UserErrorStatus(
    override val status: HttpStatus,
    override val message: String,
) : BaseErrorCode {
    NAME_IS_ALREADY_USED(HttpStatus.CONFLICT, "User name %s is already used"),
    NAME_IS_SAME(HttpStatus.BAD_REQUEST, "User name %s is same"),
    USER_IS_NOT_ACTIVE(HttpStatus.CONFLICT, "User is not active status"),
    USER_IS_NOT_FOUND(HttpStatus.NOT_FOUND, "User is not found"),
    USER_IS_NOT_CONSUMER(HttpStatus.CONFLICT, "User is not consumer"),
    USER_IS_NOT_OWNER(HttpStatus.CONFLICT, "User is not owner"),

    ;

}
