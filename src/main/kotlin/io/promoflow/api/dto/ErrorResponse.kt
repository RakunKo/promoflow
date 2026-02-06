package io.promoflow.api.dto

data class ErrorResponse(
    val status: Int,
    val message: String
) {
}