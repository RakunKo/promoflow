package io.eatda.infrastructure.persistance.entity.promotion.effect

import io.eatda.infrastructure.persistance.entity.promotion.PromotionRule
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import java.math.BigDecimal

@Entity
@DiscriminatorValue("FREE_SHIPPING")
class FreeShippingEffect(
    promotionRule: PromotionRule
) : PromotionEffect(promotionRule = promotionRule) {
    override fun effect(amount: BigDecimal): BigDecimal = amount
}