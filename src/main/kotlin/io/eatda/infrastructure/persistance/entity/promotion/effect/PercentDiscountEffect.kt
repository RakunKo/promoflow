
package io.eatda.infrastructure.persistance.entity.promotion.effect

import io.eatda.infrastructure.persistance.entity.promotion.PromotionRule
import jakarta.persistence.Column
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import java.math.BigDecimal

@Entity
@DiscriminatorValue("PERCENT_DISCOUNT")
class PercentDiscountEffect(
    var discountRate: Double,
    @Column(precision = 19, scale = 4)
    var maxDiscountAmount: BigDecimal?,
    promotionRule: PromotionRule
) : PromotionEffect(promotionRule) {

    override fun effect(amount: BigDecimal): BigDecimal {
        val calculatedDiscount = amount.multiply(BigDecimal.valueOf(discountRate))
            .divide(BigDecimal("100"))

        return maxDiscountAmount?.let { calculatedDiscount.min(it) } ?: calculatedDiscount
    }
}