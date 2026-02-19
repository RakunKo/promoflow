package io.eatda.core.common.code

import org.springframework.http.HttpStatus

interface BaseErrorCode {
    val status: HttpStatus
    val message: String
}
