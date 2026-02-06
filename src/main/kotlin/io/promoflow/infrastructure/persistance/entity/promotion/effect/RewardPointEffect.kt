package io.promoflow.infrastructure.persistance.entity.promotion.effect

import io.promoflow.infrastructure.persistance.entity.promotion.PromotionRule
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import java.math.BigDecimal

@Entity
@DiscriminatorValue("REWARD_POINT")
class RewardPointEffect(
    val rewardAmount: BigDecimal,
    val isPercent: Boolean = false,
    promotionRule: PromotionRule
) : PromotionEffect(promotionRule) {

    override fun effect(amount: BigDecimal): BigDecimal {
        return if (isPercent) {
            amount.multiply(rewardAmount.divide(BigDecimal("100")))
        } else {
            rewardAmount
        }
    }
}