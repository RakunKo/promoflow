package io.eatda.api.dto

data class ErrorResponse(
    val status: Int,
    val message: String
) {
}