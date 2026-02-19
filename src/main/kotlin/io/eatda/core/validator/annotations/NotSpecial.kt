package io.eatda.core.validator.annotations

import io.eatda.core.validator.validate.NoSpecialValidator
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
