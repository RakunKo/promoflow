package io.eatda.core.validator.validate

import io.eatda.core.validator.annotations.MinMax
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class MinMaxValidator : ConstraintValidator<MinMax, Int> {

    private var min: Int = 0
    private var max: Int = 0
    override fun initialize(constraintAnnotation: MinMax) {
        min = constraintAnnotation.min
        max = constraintAnnotation.max
    }
    override fun isValid(
        p0: Int?,
        p1: ConstraintValidatorContext,
    ): Boolean {
        if(p0 == null) return true
        return p0 in min..max
    }
}
