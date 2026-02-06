
package io.promoflow.infrastructure.persistance.entity.promotion.effect

import io.promoflow.infrastructure.persistance.entity.promotion.PromotionRule
import jakarta.persistence.Column
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import java.math.BigDecimal

@Entity
@DiscriminatorValue("AMOUNT_DISCOUNT")
class AmountDiscountEffect(
    @Column(precision = 19, scale = 4)
    var discountAmount: BigDecimal,
    promotionRule: PromotionRule
) : PromotionEffect(promotionRule = promotionRule) {
    override fun effect(amount: BigDecimal): BigDecimal = amount - discountAmount
}