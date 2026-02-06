package io.promoflow.core.handler

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

fun <R> handleApi(
    status: HttpStatus = HttpStatus.OK,
    supplier: () -> R,
): ResponseEntity<R> {
    return ResponseEntity.status(status).body(supplier())
}