package io.promoflow.core.validator.validate

import io.promoflow.core.common.utils.StringValidator.containsSpecialCharacter
import io.promoflow.core.validator.annotations.NotSpecial
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class NoSpecialValidator : ConstraintValidator<NotSpecial, String> {
    override fun isValid(
        p0: String?,
        p1: ConstraintValidatorContext?,
    ): Boolean {
        if (p0 == null) return true
        return !p0.containsSpecialCharacter()
    }
}
