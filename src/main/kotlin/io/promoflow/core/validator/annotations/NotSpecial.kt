package io.promoflow.core.validator.annotations

import io.promoflow.core.validator.validate.NoSpecialValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [NoSpecialValidator::class])
annotation class NotSpecial(
    val message: String = "Must not contain special characters",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
)
