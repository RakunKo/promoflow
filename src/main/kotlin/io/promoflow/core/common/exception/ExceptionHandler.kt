package io.promoflow.core.common.exception

import io.promoflow.api.dto.ErrorResponse
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val messages = ex.bindingResult.fieldErrors.joinToString(", ") { err ->
            "${err.field}: ${err.defaultMessage ?: "Invalid value"}"
        }

        val response = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            message = messages,
        )

        return ResponseEntity.badRequest().body(response)
    }

    @ExceptionHandler(ApiException::class)
    fun handleApiException(ex: ApiException): ResponseEntity<ErrorResponse> =
        ResponseEntity.status(ex.status).body(ex.toResponse())

    @ExceptionHandler(InfraException::class)
    fun handleInfraException(ex: InfraException): ResponseEntity<ErrorResponse> =
        ResponseEntity.status(ex.status).body(ex.toResponse())

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolation(ex: ConstraintViolationException): ResponseEntity<ErrorResponse> {
        val messages = ex.constraintViolations.joinToString(", ") { violation ->
            "${violation.propertyPath}: ${violation.message}"
        }

        val response = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            message = messages,
        )

        return ResponseEntity.badRequest().body(response)
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleTypeMismatch(ex: MethodArgumentTypeMismatchException): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            message = "Invalid request value: ${ex.name} should be of type ${ex.requiredType?.simpleName}",
        )

        return ResponseEntity.badRequest().body(response)
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = "An unexpected error occurred: ${ex.message}",
        )

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response)
    }
}