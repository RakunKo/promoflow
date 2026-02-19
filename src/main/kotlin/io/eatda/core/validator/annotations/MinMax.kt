package io.eatda.core.validator.annotations

import io.eatda.core.validator.validate.MinMaxValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [MinMaxValidator::class])
annotation class MinMax(
    val min: Int,
    val max: Int,

    val message: String = "Value must be between {min} and {max}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
)
