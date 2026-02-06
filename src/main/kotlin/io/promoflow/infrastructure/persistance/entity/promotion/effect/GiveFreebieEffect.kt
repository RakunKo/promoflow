package io.promoflow.infrastructure.persistance.entity.promotion.effect

import io.promoflow.infrastructure.persistance.entity.promotion.PromotionRule
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import java.math.BigDecimal

@Entity
@DiscriminatorValue("GIVE_FREEBIE")
class GiveFreebieEffect(
    val giftProductId: Long,
    val quantity: Int = 1,
    promotionRule: PromotionRule
) : PromotionEffect(promotionRule = promotionRule) {
    override fun effect(amount: BigDecimal): BigDecimal = BigDecimal.ZERO
}